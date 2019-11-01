package com.springproject.dtos;

public class MeasureDescriptionDto {
	private int acceptNo;
	public int getAcceptNo() {
		return acceptNo;
	}
	public void setAcceptNo(int acceptNo) {
		this.acceptNo = acceptNo;
	}
	public String getMeasureDescription() {
		return measureDescription;
	}
	public void setMeasureDescription(String measureDescription) {
		this.measureDescription = measureDescription;
	}
	private String measureDescription;
}
