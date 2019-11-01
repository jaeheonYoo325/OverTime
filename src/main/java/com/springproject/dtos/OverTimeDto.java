package com.springproject.dtos;

public class OverTimeDto {
	private int acceptNo;
	private String acceptDate;
	private String acceptTime;
	private String accepter;
	private String caller;
	private String phoneNumber;
	private String acceptDescription;
	private String measureTime;
	private String cause;
	private String measures;
	private String relatedChain;
	private String remarks;
	private String typeOfProcessing;
	public int getAcceptNo() {
		return acceptNo;
	}
	public void setAcceptNo(int acceptNo) {
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
	public String getRelatedChain() {
		return relatedChain;
	}
	public void setRelatedChain(String relatedChain) {
		this.relatedChain = relatedChain;
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
	private String statusCode;
}
