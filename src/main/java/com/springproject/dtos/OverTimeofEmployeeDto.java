package com.springproject.dtos;

public class OverTimeofEmployeeDto {
	private String employeeNo;
	private String amountOfAnnualVacation;
	private String employeeName;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getAmountOfAnnualVacation() {
		return amountOfAnnualVacation;
	}
	public void setAmountOfAnnualVacation(String amountOfAnnualVacation) {
		this.amountOfAnnualVacation = amountOfAnnualVacation;
	}
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
