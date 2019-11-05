package com.springproject.dtos;

public class MeasureDescriptionDto {
	private Long acceptNo;
	public Long getAcceptNo() {
		return acceptNo;
	}
	public void setAcceptNo(Long acceptNo) {
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
