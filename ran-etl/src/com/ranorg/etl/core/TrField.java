package com.ranorg.etl.core;

public class TrField {

	private String name;
	private int start;
	private int end;
	
	public TrField(String name, int start, int end) {
		super();
		this.name = name;
		this.start = start;
		this.end = end;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "TrField [name=" + name + ", start=" + start + ", end=" + end
				+ "]";
	}
}
