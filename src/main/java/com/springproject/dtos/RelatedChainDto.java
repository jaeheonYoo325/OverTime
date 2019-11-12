package com.springproject.dtos;

public class RelatedChainDto {
	private Long acceptNo;
	private String relatedChain;
	private String chainName;
	public String getChainName() {
		return chainName;
	}
	public void setChainName(String chainName) {
		this.chainName = chainName;
	}
	public Long getAcceptNo() {
		return acceptNo;
	}
	public void setAcceptNo(Long acceptNo) {
		this.acceptNo = acceptNo;
	}
	public String getRelatedChain() {
		return relatedChain;
	}
	public void setRelatedChain(String relatedChain) {
		this.relatedChain = relatedChain;
	}
}
