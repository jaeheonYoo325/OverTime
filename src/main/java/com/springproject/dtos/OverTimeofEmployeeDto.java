package com.springproject.dtos;

public class OverTimeofEmployeeDto {
	private String employeeNo;
	private String employeeName;
	private String sumOfOverTime;
	private String extensionOverTime;
	private String nightTimeOvertime;
	private String holidayOvertimeOfExceed8Hours;
	private String holidayOvertimeOfNotExceed8Hours;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getSumOfOverTime() {
		return sumOfOverTime;
	}
	public void setSumOfOverTime(String sumOfOverTime) {
		this.sumOfOverTime = sumOfOverTime;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getExtensionOverTime() {
		return extensionOverTime;
	}
	public void setExtensionOverTime(String extensionOverTime) {
		this.extensionOverTime = extensionOverTime;
	}
	public String getNightTimeOvertime() {
		return nightTimeOvertime;
	}
	public void setNightTimeOvertime(String nightTimeOvertime) {
		this.nightTimeOvertime = nightTimeOvertime;
	}
	public String getHolidayOvertimeOfExceed8Hours() {
		return holidayOvertimeOfExceed8Hours;
	}
	public void setHolidayOvertimeOfExceed8Hours(String holidayOvertimeOfExceed8Hours) {
		this.holidayOvertimeOfExceed8Hours = holidayOvertimeOfExceed8Hours;
	}
	public String getHolidayOvertimeOfNotExceed8Hours() {
		return holidayOvertimeOfNotExceed8Hours;
	}
	public void setHolidayOvertimeOfNotExceed8Hours(String holidayOvertimeOfNotExceed8Hours) {
		this.holidayOvertimeOfNotExceed8Hours = holidayOvertimeOfNotExceed8Hours;
	}
}
