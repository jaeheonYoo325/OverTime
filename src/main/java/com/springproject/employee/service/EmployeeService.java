package com.springproject.employee.service;

import com.springproject.employee.dto.EmployeeDto;

public interface EmployeeService {

	public boolean insertOneEmployeeService(EmployeeDto employeeDto);
	
	public EmployeeDto selectOneEmployeeService(EmployeeDto employeeDto);
	
	public int duplicateCheckOfEmployeeNoService(String employeeNo);
}
