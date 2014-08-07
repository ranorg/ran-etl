package com.ranorg.etl.record;

import java.util.LinkedHashSet;

import com.ranorg.etl.exception.ETLException;

public abstract class FieldFactory {

	public abstract void initialize() throws ETLException;

	public abstract LinkedHashSet<String> getFieldNames(String type);
}
