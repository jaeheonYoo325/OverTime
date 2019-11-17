package com.springproject.overtime.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import com.springproject.dtos.CategoryTypeDto;
import com.springproject.dtos.ChainTableDto;
import com.springproject.dtos.InterPhoneDto;
import com.springproject.dtos.MasterTableDto;
import com.springproject.dtos.MeasureDescriptionDto;
import com.springproject.dtos.MeasurerDto;
import com.springproject.dtos.OverTimeDto;
import com.springproject.dtos.OverTimeofEmployeeDto;
import com.springproject.dtos.RelatedChainDto;
import com.springproject.employee.dto.EmployeeDto;

public interface OverTimeService {

	public List<EmployeeDto> selectAllEmployeeService();

	public List<EmployeeDto> selectEmployeeOfSearchService(EmployeeDto employeeDto);

	public List<InterPhoneDto> sellectAllInterPhoneService();

	public List<InterPhoneDto> selectCallerOfSearchService(InterPhoneDto interPhoneDto);

	public List<ChainTableDto> selectAllChainService();

	public List<ChainTableDto> selectChainOfSearchService(ChainTableDto chainTableDto);

	public boolean insertOverTimeRequestService(OverTimeDto overtimeDto, ArrayList<String> measurer,ArrayList<String> measureDescription, ArrayList<String> relatedChain);

	public List<MasterTableDto> selectMasterCodeOfCategoryService();

	public Map<String, List<MasterTableDto>> selectCategoryMasterCodesOfCodeTypeService(List<MasterTableDto> masterCodeOfCategory);

	public Map<String, List<MasterTableDto>> selectMasterCodeOfSearchTypeMapService(String searchTypeString);

	public List<OverTimeDto> selectCategoryOverTimeRequestService(CategoryTypeDto categoryTypeDto);

	public List<OverTimeDto> selectAllOverTimeRequestService();

	public List<MeasurerDto> selectMeasurerOfAcceptNoService(Long acceptNo);

	public List<MeasureDescriptionDto> selectMeasureDescriptionOfAcceptNoService(Long acceptNo);

	public OverTimeofEmployeeDto selectOverTimeofEmployeeService(EmployeeDto employeeDto);
	
	public OverTimeDto selectOverTimeRequestOfAcceptNoService(Long acceptNo);
	
	public boolean overTimeReRequestService(OverTimeDto overTimeDto, ArrayList<String> measurer, ArrayList<String> measureDescription, ArrayList<String> relatedChain);

	public List<RelatedChainDto> selectRelatedChainOfAcceptNoService(Long acceptNo);
	
	public List<InterPhoneDto> selectCallerOfSearchAjaxService(String caller);
	
	public List<EmployeeDto> selectEmployeeOfSearchAjaxService(String measurer);
	
	public List<ChainTableDto> selectChainOfSearchAjaxService(String relatedChain);
}
