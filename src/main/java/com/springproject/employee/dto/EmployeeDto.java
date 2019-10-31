package com.springproject.employee.dto;

import javax.validation.constraints.NotEmpty;

import com.springproject.common.validator.employee.EmployeeValidator;

public class EmployeeDto {

	@NotEmpty(message = "사원번호는 필수 입력 값입니다.", groups = { EmployeeValidator.Regist.class, EmployeeValidator.Login.class })
	private String employeeNo;
	@NotEmpty(message = "이름은 필수 입력 값입니다.", groups = { EmployeeValidator.Regist.class })
	private String employeeName;
	@NotEmpty(message = "비밀번호는 필수 입력 값입니다.", groups = { EmployeeValidator.Regist.class, EmployeeValidator.Login.class }) //
	private String employeePassWord;
	@NotEmpty(message = "비밀번호 확인 필수 입력 값입니다.", groups = { EmployeeValidator.Regist.class })
	private String employeePassWordConfirm;
	@NotEmpty(message = "휴대폰 번호는 필수 입력 값입니다.", groups = { EmployeeValidator.Regist.class })
	private String employeePhoneNumber;
	@NotEmpty(message = "우편번호는 필수 입력 값입니다.", groups = { EmployeeValidator.Regist.class })
	private String employeePostcode;
	@NotEmpty(message = "주소는 필수 입력 값입니다.", groups = { EmployeeValidator.Regist.class })
	private String employeeCommonaddress;
	@NotEmpty(message = "상세 주소는 필수 입력 값입니다.", groups = { EmployeeValidator.Regist.class })
	private String employeeDetailaddress;
	@NotEmpty(message = "회사 번호는 필수 입력 값입니다.", groups = { EmployeeValidator.Regist.class })
	private String employeeCompanyPhoneNumber;
	@NotEmpty(message = "이메일은 필수 입력 값입니다.", groups = { EmployeeValidator.Regist.class })
	private String employeeEmail;
	@NotEmpty(message = "입사일은 필수 입력 값입니다.", groups = { EmployeeValidator.Regist.class })
	private String employeeJoinDate;
	private String employeeRegisterDate;
	private String employeeModifyDate;
	private String salt;
//	@NotEmpty(message = "부서 번호는 필수 입력 값입니다.", groups = { EmployeeValidator.Regist.class })
	private String departmentNo;
	@NotEmpty(message = "직급 번호는 필수 입력 값입니다.", groups = { EmployeeValidator.Regist.class })
	private String positionNo;

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeePassWord() {
		return employeePassWord;
	}

	public void setEmployeePassWord(String employeePassWord) {
		this.employeePassWord = employeePassWord;
	}

	public String getEmployeePassWordConfirm() {
		return employeePassWordConfirm;
	}

	public void setEmployeePassWordConfirm(String employeePassWordConfirm) {
		this.employeePassWordConfirm = employeePassWordConfirm;
	}

	public String getEmployeePhoneNumber() {
		return employeePhoneNumber;
	}

	public void setEmployeePhoneNumber(String employeePhoneNumber) {
		this.employeePhoneNumber = employeePhoneNumber;
	}

	public String getEmployeePostcode() {
		return employeePostcode;
	}

	public void setEmployeePostcode(String employeePostcode) {
		this.employeePostcode = employeePostcode;
	}

	public String getEmployeeCommonaddress() {
		return employeeCommonaddress;
	}

	public void setEmployeeCommonaddress(String employeeCommonaddress) {
		this.employeeCommonaddress = employeeCommonaddress;
	}

	public String getEmployeeDetailaddress() {
		return employeeDetailaddress;
	}

	public void setEmployeeDetailaddress(String employeeDetailaddress) {
		this.employeeDetailaddress = employeeDetailaddress;
	}

	public String getEmployeeCompanyPhoneNumber() {
		return employeeCompanyPhoneNumber;
	}

	public void setEmployeeCompanyPhoneNumber(String employeeCompanyPhoneNumber) {
		this.employeeCompanyPhoneNumber = employeeCompanyPhoneNumber;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeJoinDate() {
		return employeeJoinDate;
	}

	public void setEmployeeJoinDate(String employeeJoinDate) {
		this.employeeJoinDate = employeeJoinDate;
	}

	public String getEmployeeRegisterDate() {
		return employeeRegisterDate;
	}

	public void setEmployeeRegisterDate(String employeeRegisterDate) {
		this.employeeRegisterDate = employeeRegisterDate;
	}

	public String getEmployeeModifyDate() {
		return employeeModifyDate;
	}

	public void setEmployeeModifyDate(String employeeModifyDate) {
		this.employeeModifyDate = employeeModifyDate;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String getPositionNo() {
		return positionNo;
	}

	public void setPositionNo(String positionNo) {
		this.positionNo = positionNo;
	}

}
