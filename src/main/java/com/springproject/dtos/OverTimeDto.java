package com.springproject.dtos;

import javax.validation.constraints.NotEmpty;

public class OverTimeDto {
	
	private Long acceptNo;
	@NotEmpty(message = "접수 날짜는 필수 입력 값입니다.")
	private String acceptDate;
	@NotEmpty(message = "접수 시간은 필수 입력 값입니다.")
	private String acceptTime;
	@NotEmpty(message = "접수자는 필수 입력 값입니다.")
	private String accepter;
	@NotEmpty(message = "발신자는 필수 입력 값입니다.")
	private String caller;
	@NotEmpty(message = "전화번호는 필수 입력 값입니다.")
	private String phoneNumber;
	@NotEmpty(message = "접수내용은 필수 입력 값입니다.")
	private String acceptDescription;
	@NotEmpty(message = "조치시간은 필수 입력 값입니다.")
	private String measureTime;
	@NotEmpty(message = "원인 내용은 필수 입력 값입니다.")
	private String cause;
	@NotEmpty(message = "대책 내용은 필수 입력 값입니다.")
	private String measures;
	private String remarks;
	private String typeOfProcessing;
	private String statusCode;
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

	public String getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAcceptDescription() {
		return acceptDescription;
	}

	public void setAcceptDescription(String acceptDescription) {
		this.acceptDescription = acceptDescription;
	}

	public String getMeasureTime() {
		return measureTime;
	}

	public void setMeasureTime(String measureTime) {
		this.measureTime = measureTime;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getMeasures() {
		return measures;
	}

	public void setMeasures(String measures) {
		this.measures = measures;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTypeOfProcessing() {
		return typeOfProcessing;
	}

	public void setTypeOfProcessing(String typeOfProcessing) {
		this.typeOfProcessing = typeOfProcessing;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	
}
