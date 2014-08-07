package com.ranorg.etl.exception;

public class ETLException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ETLException() {
		super();
	}

	public ETLException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ETLException(String message, Throwable cause) {
		super(message, cause);
	}

	public ETLException(String message) {
		super(message);
	}

	public ETLException(Throwable cause) {
		super(cause);
	}

}
