package com.springproject.dtos;

public class OverTimeApprovalDto {
	private Long acceptNo;
	public Long getAcceptNo() {
		return acceptNo;
	}
	public void setAcceptNo(Long acceptNo) {
		this.acceptNo = acceptNo;
	}
	public String getDrafter() {
		return Drafter;
	}
	public void setDrafter(String drafter) {
		Drafter = drafter;
	}
	public String getDraftDate() {
		return DraftDate;
	}
	public void setDraftDate(String draftDate) {
		DraftDate = draftDate;
	}
	public String getApprovalRequestDate() {
		return ApprovalRequestDate;
	}
	public void setApprovalRequestDate(String approvalRequestDate) {
		ApprovalRequestDate = approvalRequestDate;
	}
	public String getApprovalLine() {
		return ApprovalLine;
	}
	public void setApprovalLine(String approvalLine) {
		ApprovalLine = approvalLine;
	}
	public String getApprovalDescription() {
		return ApprovalDescription;
	}
	public void setApprovalDescription(String approvalDescription) {
		ApprovalDescription = approvalDescription;
	}
	public String getApprovalDate() {
		return ApprovalDate;
	}
	public void setApprovalDate(String approvalDate) {
		ApprovalDate = approvalDate;
	}
	public String getApprovalLineConfirm() {
		return ApprovalLineConfirm;
	}
	public void setApprovalLineConfirm(String approvalLineConfirm) {
		ApprovalLineConfirm = approvalLineConfirm;
	}
	private String Drafter;
	private String DraftDate;
	private String ApprovalRequestDate;
	private String ApprovalLine;
	private String ApprovalDescription;
	private String ApprovalDate;
	private String ApprovalLineConfirm;
}
