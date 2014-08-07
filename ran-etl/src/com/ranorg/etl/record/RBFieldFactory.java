package com.ranorg.etl.record;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.ranorg.etl.exception.ETLException;

public class RBFieldFactory extends FieldFactory {

	private static final Logger log = Logger.getLogger(RBFieldFactory.class);
	private Map<String, LinkedHashSet<String>> map = new HashMap<String, LinkedHashSet<String>>();

	@Override
	public void initialize() throws ETLException {
		log.debug("RBFieldFactory initialization starts");
		try {
			ResourceBundle rb = ResourceBundle.getBundle("dr");
			Enumeration<String> keys = rb.getKeys();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				StringTokenizer st = new StringTokenizer(rb.getString(key), ",");
				LinkedHashSet<String> list = new LinkedHashSet<String>();
				while (st.hasMoreTokens()) {
					list.add(st.nextToken());
				}
				map.put(key, list);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ETLException(e);
		}
		log.debug("RBFieldFactory initialization ends");
	}

	@Override
	public LinkedHashSet<String> getFieldNames(String type) {
		return  map.get(type);
	}

}
