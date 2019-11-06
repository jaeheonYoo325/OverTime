package com.springproject.employee.dao;

import com.springproject.employee.dto.EmployeeDto;

public interface EmployeeDao {

	public int insertOneEmployeeDao(EmployeeDto employeeDto);
	
	public EmployeeDto selectOneEmployeeDao(EmployeeDto employeeDto);
	
	public String getSaltByEmployeeNumber(String empNo);
	
	public int duplicateCheckOfEmployeeNoDao(String employeeNo);

	public int isInsertOneOvertimeOfEmployeeDao(EmployeeDto employeeDto);
	
}
