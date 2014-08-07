package com.ranorg.etl.exception;

public class NoMoreRecordException extends ETLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMoreRecordException() {
		super();
	}

	public NoMoreRecordException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoMoreRecordException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoMoreRecordException(String message) {
		super(message);
	}

	public NoMoreRecordException(Throwable cause) {
		super(cause);
	}

}
