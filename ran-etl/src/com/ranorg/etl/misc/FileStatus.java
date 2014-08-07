package com.ranorg.etl.misc;

import java.util.Date;

public class FileStatus {

	public static final String RUNNING = "RUNNING";
	public static final String SUCCESSFUL = "SUCCESSFUL";
	public static final String FAIL = "FAIL";
	public static final String INITIALIZED = "INITIALIZED";
	private String fileName;
	private String source;
	private String status;
	private Date startDate;
	private Date endDate;
	private int parseCount;
	private long fileSeqNumber;
	private String errorMessage;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getParseCount() {
		return parseCount;
	}

	public void setParseCount(int parseCount) {
		this.parseCount = parseCount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public long getFileSeqNumber() {
		return fileSeqNumber;
	}

	public void setFileSeqNumber(long fileSeqNumber) {
		this.fileSeqNumber = fileSeqNumber;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		if (errorMessage != null && errorMessage.length() > 200) {
			errorMessage = errorMessage.substring(0, 199);
		}
		this.errorMessage = errorMessage;
	}
}
