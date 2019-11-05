package com.springproject.dtos;

public class OverTimeApprovalDto {
	private Long acceptNo;
	private String drafter;
	private String draftDate;
	private String approvalRequestDate;
	private String approvalLine;
	private String approvalDescription;
	private String approvalDate;
	public Long getAcceptNo() {
		return acceptNo;
	}
	public void setAcceptNo(Long acceptNo) {
		this.acceptNo = acceptNo;
	}
	public String getDrafter() {
		return drafter;
	}
	public void setDrafter(String drafter) {
		this.drafter = drafter;
	}
	public String getDraftDate() {
		return draftDate;
	}
	public void setDraftDate(String draftDate) {
		this.draftDate = draftDate;
	}
	public String getApprovalRequestDate() {
		return approvalRequestDate;
	}
	public void setApprovalRequestDate(String approvalRequestDate) {
		this.approvalRequestDate = approvalRequestDate;
	}
	public String getApprovalLine() {
		return approvalLine;
	}
	public void setApprovalLine(String approvalLine) {
		this.approvalLine = approvalLine;
	}
	public String getApprovalDescription() {
		return approvalDescription;
	}
	public void setApprovalDescription(String approvalDescription) {
		this.approvalDescription = approvalDescription;
	}
	public String getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	public String getApprovalLineConfirm() {
		return approvalLineConfirm;
	}
	public void setApprovalLineConfirm(String approvalLineConfirm) {
		this.approvalLineConfirm = approvalLineConfirm;
	}
	private String approvalLineConfirm;
}
