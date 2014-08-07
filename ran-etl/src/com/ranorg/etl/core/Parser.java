package com.ranorg.etl.core;

import java.util.LinkedHashSet;

import com.ranorg.etl.exception.ETLException;
import com.ranorg.etl.misc.ETLConstants;
import com.ranorg.etl.record.Record;

public abstract class Parser {

	public abstract String getSourceName();

	public abstract void initialize(String inputFile) throws ETLException;

	public abstract Record getNextRecord() throws ETLException;

	public LinkedHashSet<String> getAllFieldNames(String type) {
		LinkedHashSet<String> list = getFieldNames(type);
		LinkedHashSet<String> allList = new LinkedHashSet<String>();
		allList.add(ETLConstants.FILE_NAME);
		allList.add(ETLConstants.RECORD_NAME);
		allList.addAll(list);
		return allList;
	}

	public abstract LinkedHashSet<String> getFieldNames(String type);
}
