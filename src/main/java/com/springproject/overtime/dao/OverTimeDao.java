package com.springproject.overtime.dao;

import java.util.List;

import com.springproject.dtos.ChainTableDto;
import com.springproject.dtos.InterPhoneDto;
import com.springproject.dtos.MeasureDescriptionDto;
import com.springproject.dtos.MeasurerDto;
import com.springproject.dtos.OverTimeApprovalDto;
import com.springproject.dtos.OverTimeDto;
import com.springproject.employee.dto.EmployeeDto;

public interface OverTimeDao {

	public List<EmployeeDto> selectAllEmployeeDao();

	public List<EmployeeDto> selectEmployeeOfSearchDao(EmployeeDto employeeDto);

	public List<InterPhoneDto> sellectAllInterPhoneDao();

	public List<InterPhoneDto> selectCallerOfSearchDao(InterPhoneDto interPhoneDto);

	public List<ChainTableDto> selectAllChainDao();

	public List<ChainTableDto> selectChainOfSearchDao(ChainTableDto chainTableDto);

	public int insertOverTimeRequestDao(OverTimeDto overtimeDto);

	public Long selectMaxAcceptNoDao();

	public int insertMeasurerDao(MeasurerDto measurerDto);

	public int insertMeasureDescriptionDao(MeasureDescriptionDto measureDescriptionDto);

	public int InsertOverTimeApprovalForOverTimeRequestDao(OverTimeApprovalDto overTimeApprovalDto);
}
