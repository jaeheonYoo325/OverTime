package com.springproject.overtime.service;

import java.util.ArrayList;
import java.util.List;

import com.springproject.dtos.ChainTableDto;
import com.springproject.dtos.InterPhoneDto;
import com.springproject.dtos.OverTimeDto;
import com.springproject.employee.dto.EmployeeDto;

public interface OverTimeService {

	public List<EmployeeDto> selectAllEmployeeService();

	public List<EmployeeDto> selectEmployeeOfSearchService(EmployeeDto employeeDto);

	public List<InterPhoneDto> sellectAllInterPhoneService();

	public List<InterPhoneDto> selectCallerOfSearchService(InterPhoneDto interPhoneDto);

	public List<ChainTableDto> selectAllChainService();

	public List<ChainTableDto> selectChainOfSearchService(ChainTableDto chainTableDto);

	public boolean insertOverTimeRequestService(OverTimeDto overtimeDto, ArrayList<String> measurer,ArrayList<String> measureDescription);
}
