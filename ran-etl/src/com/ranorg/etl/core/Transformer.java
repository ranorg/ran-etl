package com.ranorg.etl.core;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.ranorg.etl.exception.ETLException;
import com.ranorg.etl.misc.ETLConstants;
import com.ranorg.etl.misc.HexUtil;
import com.ranorg.etl.record.DataRecord;
import com.ranorg.etl.record.DataRecord.Field;

public abstract class Transformer {

	private static final Logger log = Logger.getLogger(Transformer.class);
	private Map<String, List<String>> trbcdMap = new HashMap<String, List<String>>();
	private Map<String, List<TrField>> trhexToDecMap = new HashMap<String, List<TrField>>();

	public void initialize() throws ETLException {
		log.debug("Transformer initialization starts");
		initializeTrBCD();
		initializeTrHexToDec();
		log.debug("Transformer initialization ends");
	}

	private void initializeTrBCD() throws ETLException {
		try {
			ResourceBundle trbcdRb = ResourceBundle.getBundle("tr-tbcd");
			Enumeration<String> keys = trbcdRb.getKeys();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				StringTokenizer st = new StringTokenizer(
						trbcdRb.getString(key), ",");
				List<String> list = new ArrayList<String>();
				while (st.hasMoreTokens()) {
					list.add(st.nextToken());
				}
				trbcdMap.put(key, list);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ETLException(e);
		}
	}

	private void initializeTrHexToDec() throws ETLException {
		try {
			ResourceBundle hexToDec = ResourceBundle.getBundle("tr-hexToDec");
			Enumeration<String> keys = hexToDec.getKeys();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				StringTokenizer st = new StringTokenizer(
						hexToDec.getString(key), ",");
				List<TrField> list = new ArrayList<TrField>();
				while (st.hasMoreTokens()) {
					TrField tf = createTRField(st.nextToken());
					log.info(tf);
					list.add(tf);
				}
				trhexToDecMap.put(key, list);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ETLException(e);
		}
	}

	private TrField createTRField(String nextToken) {
		String name = "";
		int start = 0;
		int end = -1;
		String[] arr = nextToken.split("-");
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				name = arr[0];
			} else if (i == 1) {
				try {
					start = Integer.parseInt(arr[1]);
				} catch (NumberFormatException e) {
					log.error(e.getMessage(), e);
				} catch (NullPointerException e) {
					log.error(e.getMessage(), e);
				}
			} else if (i == 2) {
				try {
					end = Integer.parseInt(arr[2]);
				} catch (NumberFormatException e) {
					log.error(e.getMessage(), e);
				} catch (NullPointerException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		TrField tf = new TrField(name, start, end);
		return tf;
	}

	public void transform(DataRecord datarecord) throws ETLException {
		try {
			transformTBCD(datarecord);
			transformHexToDec(datarecord);
			transformStringToIP(datarecord);
			transformLocation(datarecord);
			transformSpecific(datarecord);
		} catch (Exception e) {
			throw new ETLException(e);
		}
	}

	private void transformHexToDec(DataRecord datarecord) {
		String type = datarecord.getFields(ETLConstants.RECORD_NAME).getValue();
		List<TrField> list = trhexToDecMap.get(type);
		if (list != null) {
			for (TrField tf : list) {
				Field fld = datarecord.getFields(tf.getName());
				if (fld != null) {
					int startIndex = 0;
					int endIndex = fld.getValue().length();
					if (tf.getStart() > -1) {
						startIndex = tf.getStart();
					}
					if (tf.getEnd() > -1) {
						endIndex = tf.getEnd() + 1;
					}
					String val = fld.getValue().substring(startIndex, endIndex);
					//log.info(fld.getValue()+":"+startIndex + ":" + endIndex + ":" + val);
					fld.setValue(hexToDec(val) + "");
				}
			}
		}
	}

	private void transformTBCD(DataRecord datarecord) {
		String type = datarecord.getFields(ETLConstants.RECORD_NAME).getValue();
		List<String> list = trbcdMap.get(type);
		if (list != null) {
			for (String fldName : list) {
				Field fld = datarecord.getFields(fldName);
				if (fld != null) {
					fld.setValue(trimLastCharF(interchange(fld.getValue())));
				}
			}
		}
	}

	private void transformStringToIP(DataRecord dr) {
		for (Field fld : dr.getFields()) {
			if (fld.getName().equals("sgsnAddress")
					|| fld.getName().equals("servedPDPAddress")
					|| fld.getName().equals("ggsnAddressUsed")) {
				String val = fld.getValue();
				if (val != null && val.length() == 8) {
					String delim = "";
					for (int i = 0; i < 8; i += 2) {
						if (i == 0)
							fld.setValue("");
						fld.setValue(fld.getValue() + delim
								+ Integer.parseInt(val.substring(i, i + 2), 16));
						delim = ".";
					}
				}
			}
		}
	}

	public void transformLocation(DataRecord datarecord) throws ETLException {
		for (Field fld : datarecord.getFields()) {
			if (fld.getName().equals("location")) {
				String val = fld.getValue();
				if (val != null && val.length() >= 9) {
					fld.setValue(HexUtil.hex2decimal(val.substring(0, 4)) + "|"
							+ HexUtil.hex2decimal(val.substring(5, 9)));
				}
			}
		}
	}

	public abstract void transformSpecific(DataRecord datarecord)
			throws ETLException;

	public String interchange(String input) {
		String ret = input;
		if (input != null && input.trim().length() > 0) {
			ret = "";
			for (int i = 0; i < input.length() - 1; i = i + 2) {
				Character char1 = getCharacter(input, i);
				Character char2 = getCharacter(input, i + 1);
				Character charT = char1;
				char1 = char2;
				char2 = charT;
				ret += "" + char1 + char2;
			}
			if (input.length() % 2 != 0) {
				ret += input.charAt(input.length() - 1);
			}
		}
		return ret;
	}

	private Character getCharacter(String input, int index) {
		try {
			return input.charAt(index);
		} catch (Exception e) {
			return null;
		}
	}

	public String trimLastChar(String input, Character ch) {
		if (input != null && input.endsWith(ch + "")) {
			input = input.substring(0, input.length() - 1);
		}
		return input;
	}

	public String trimLastCharF(String input) {
		return trimLastChar(input, 'F');
	}

	public String hexToDec(String input) {
		try {
			return Long.parseLong(input, 16) + "";
		} catch (NumberFormatException e) {
			log.error(e.getMessage(), e);
		}
		return input;
	}

	public static class TestTransformer extends Transformer {

		public static void main(String[] args) {
			TestTransformer tt = new TestTransformer();
			System.out.println("ZTEMSCParser.main()"
					+ tt.trimLastChar(tt.interchange("1234F6"), 'F'));
		}

		@Override
		public void transformSpecific(DataRecord datarecord)
				throws ETLException {
			// TODO Auto-generated method stub

		}

	}
}
