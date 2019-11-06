package com.springproject.dtos;

public class MeasurerDto {
	private Long acceptNo;
	private String measurer;
	private String employeeName;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Long getAcceptNo() {
		return acceptNo;
	}
	public void setAcceptNo(Long acceptNo) {
		this.acceptNo = acceptNo;
	}
	public String getMeasurer() {
		return measurer;
	}
	public void setMeasurer(String measurer) {
		this.measurer = measurer;
	}
}
