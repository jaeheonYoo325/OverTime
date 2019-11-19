package com.springproject.employee.dao;

import java.util.List;


import com.springproject.dtos.AuthorityDto;
import com.springproject.dtos.MeasurerDto;
import com.springproject.dtos.OverTimeApprovalDto;
import com.springproject.dtos.OverTimeDto;
import com.springproject.dtos.OverTimeofEmployeeDto;
import com.springproject.employee.dto.EmployeeDto;
import com.springproject.overtimeAPI.ElementsOfOverTimeForCalculate;

public interface EmployeeDao {

	public int insertOneEmployeeDao(EmployeeDto employeeDto);
	
	public EmployeeDto selectOneEmployeeDao(EmployeeDto employeeDto);
	
	public String getSaltByEmployeeNumber(String empNo);
	
	public int duplicateCheckOfEmployeeNoDao(String employeeNo);

	public int isInsertOneOvertimeOfEmployeeDao(EmployeeDto employeeDto);
	
	public List<OverTimeApprovalDto> selectMyOverTimeApprovalDao(EmployeeDto employeeDto);
	
	public OverTimeApprovalDto selectMyOverTimeApprovalOfAcceptNoDao(Long acceptNo);
	
	public int myOverTimeDoApprovalingOfCompleteNowApprovalDao(OverTimeApprovalDto overTimeApprovalDto);
		
	public int changeStatusCodeForOverTimeDoApprovalingDao(OverTimeDto overTimeDto);
	
	public List<OverTimeApprovalDto> selectMyOverTimeApprovedDao(EmployeeDto employeeDto);
	
	public OverTimeofEmployeeDto selectOverTimeOfEmployeeNoDao(String employeeNo);
	
	public List<OverTimeApprovalDto> selectMyOverTimeCompletedDao(EmployeeDto employeeDto);
	
	public List<OverTimeApprovalDto> selectMyOverTimeReturnedDao(EmployeeDto employeeDto);
	
	public List<MeasurerDto> measurerListOfAcceptNoDao(Long acceptNo);
	
	public int measurerOfMeasureTimeAccumulationDao(ElementsOfOverTimeForCalculate elementsOfOverTimeForCalculate);

	public List<AuthorityDto> checkIsThisUserHaveRequestOfOverTimeAuthorityDao(EmployeeDto employeeDto);

	public List<AuthorityDto> checkisThisUserHaveAuthorityOfEmployeeRegistDao(EmployeeDto employeeDto);

	public int myOverTimeDoApprovaling(OverTimeApprovalDto overTimeApprovalDto);
	
	public List<MeasurerDto> selectMeasurerDao(EmployeeDto employeeDto);
	
}
