package com.springproject.dtos;

public class OverTimeofEmployeeDto {
	private String employeeNo;
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getSumOfOverTime() {
		return sumOfOverTime;
	}
	public void setSumOfOverTime(String sumOfOverTime) {
		this.sumOfOverTime = sumOfOverTime;
	}
	private String sumOfOverTime;
}
