package ink.jasn.expr.devel;

import com.ranorg.etl.core.Transformer;
import com.ranorg.etl.exception.ETLException;
import com.ranorg.etl.misc.HexUtil;
import com.ranorg.etl.record.DataRecord;
import com.ranorg.etl.record.DataRecord.Field;

public class ZTEMSCTransformer extends Transformer {

	@Override
	public void transformSpecific(DataRecord datarecord) throws ETLException {

	}

	@Override
	public void transformLocation(DataRecord datarecord) throws ETLException {
		for (Field fld : datarecord.getFields()) {
			if (fld.getName().equals("location")) {
				String val = fld.getValue();
				if (val != null && val.length() >= 14) {
					String mcc = trimLastCharF(interchange(val.substring(0, 4)));
					String mnc = interchange(val.substring(4, 6));
					String lac = HexUtil.hex2decimal(val.substring(6, 10)) + "";
					String cellId = HexUtil.hex2decimal(val.substring(10, 14))
							+ "";
					datarecord.setFieldValue("mcc", mcc);
					datarecord.setFieldValue("mnc", mnc);
					datarecord.setFieldValue("lac", lac);
					datarecord.setFieldValue("cellId", cellId);
				}
			}
		}
	}

}
