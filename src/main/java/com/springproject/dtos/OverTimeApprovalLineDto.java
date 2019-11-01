package com.springproject.dtos;

public class OverTimeApprovalLineDto {
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getApprovalLineCode() {
		return approvalLineCode;
	}
	public void setApprovalLineCode(String approvalLineCode) {
		this.approvalLineCode = approvalLineCode;
	}
	public String getApprovalLineName() {
		return approvalLineName;
	}
	public void setApprovalLineName(String approvalLineName) {
		this.approvalLineName = approvalLineName;
	}
	private String employeeNo;
	private String approvalLineCode;
	private String approvalLineName;
}
