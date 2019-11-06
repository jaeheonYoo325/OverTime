package com.springproject.dtos;

public class CategoryTypeDto {

	private String categoryChain;
	private String categoryAcceptDate;
	private String categoryStatus;
	private String searchType;
	public String getCategoryChain() {
		return categoryChain;
	}

	public void setCategoryChain(String categoryChain) {
		this.categoryChain = categoryChain;
	}

	public String getCategoryAcceptDate() {
		return categoryAcceptDate;
	}

	public void setCategoryAcceptDate(String categoryAcceptDate) {
		this.categoryAcceptDate = categoryAcceptDate;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	private String searchKeyword;


	public String getCateAcceptDateString() {
		return "CTGY_AcceptDate";
	}

	public String getCateStatusString() {
		return "CTGY_STATUS";
	}

	public String getCateChainString() {
		return "CTGY_CHAIN";
	}
	
	public String getSearchTypeString() {
		return "SEARCHTYPE";
	}
}
