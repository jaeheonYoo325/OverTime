package com.springproject.employee.service;

import java.util.List;

import com.springproject.dtos.MeasurerDto;
import com.springproject.dtos.OverTimeApprovalDto;
import com.springproject.employee.dto.EmployeeDto;

public interface EmployeeService {

	public boolean insertOneEmployeeService(EmployeeDto employeeDto);
	
	public EmployeeDto selectOneEmployeeService(EmployeeDto employeeDto);
	
	public int duplicateCheckOfEmployeeNoService(String employeeNo);
	
	public List<OverTimeApprovalDto> selectMyOverTimeApprovalService(EmployeeDto employeeDto);
	
	public OverTimeApprovalDto selectMyOverTimeApprovalOfAcceptNoService(Long acceptNo);
	
	public boolean myOverTimeDoApprovalingService(OverTimeApprovalDto overTimeApprovalDto, String measureTimeForApproval);
	
	public List<OverTimeApprovalDto> selectMyOverTimeApprovedService(EmployeeDto employeeDto);
	
	public List<OverTimeApprovalDto> selectMyOverTimeCompletedService(EmployeeDto employeeDto);
	
	public boolean myOverTimeDoReturningService(OverTimeApprovalDto overTimeApprovalDto);
	
	public List<OverTimeApprovalDto> selectMyOverTimeReturnedService(EmployeeDto employeeDto);

	public boolean checkIsThisUserHaveRequestOfOverTimeAuthorityService(EmployeeDto employeeDto);

	public boolean checkisThisUserHaveAuthorityOfEmployeeRegistService(EmployeeDto employeeDto);
	
	public List<MeasurerDto> selectMeasurerService(EmployeeDto employeeDto);
}
