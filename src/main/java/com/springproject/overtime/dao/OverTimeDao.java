package com.springproject.overtime.dao;

import java.util.List;


import com.springproject.dtos.CategoryTypeDto;
import com.springproject.dtos.ChainTableDto;
import com.springproject.dtos.InterPhoneDto;
import com.springproject.dtos.MasterTableDto;
import com.springproject.dtos.MeasureDescriptionDto;
import com.springproject.dtos.MeasurerDto;
import com.springproject.dtos.OverTimeApprovalDto;
import com.springproject.dtos.OverTimeDto;
import com.springproject.dtos.OverTimeofEmployeeDto;
import com.springproject.dtos.RelatedChainDto;
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

	public List<MasterTableDto> selectMasterCodeOfCategoryDao();

	public List<MasterTableDto> selectCategoryMasterCodesOfCodeTypeDao(String codeType);

	public List<MasterTableDto> selectMasterCodeOfSearchTypeMapDao(String searchTypeString);

	public List<OverTimeDto> selectCategoryOverTimeRequestDao(CategoryTypeDto overTimeRequestForCategory);

	public List<OverTimeDto> selectAllOverTimeRequestDao();

	public List<MeasurerDto> selectMeasurerOfAcceptNoDao(Long acceptNo);

	public List<MeasureDescriptionDto> selectMeasureDescriptionOfAcceptNoDao(Long acceptNo);

	public OverTimeofEmployeeDto selectOverTimeofEmployeeDao(EmployeeDto employeeDto);
	
	public OverTimeDto selectOverTimeRequestOfAcceptNoDao(Long acceptNo);
	
	public int updateOneOverTimeRequestDao(OverTimeDto overTimeDto);
	
	public int deleteMeasurerOfAcceptNoDao(Long acceptNo);
	
	public int deleteMeasureDescriptionOfAcceptNoDao(Long acceptNo);

	public int insertRelatedChainDao(RelatedChainDto relatedChainDto);

	public List<RelatedChainDto> selectRelatedChainOfAcceptNoDao(Long acceptNo);

	public int deleteRelatedChainOfAcceptNoDao(Long acceptNo);
	
	public List<InterPhoneDto> selectCallerOfSearchAjaxDao(String caller);
	
	public List<EmployeeDto> selectEmployeeOfSearchAjaxDao(String measurer);
	
	public List<ChainTableDto> selectChainOfSearchAjaxDao(String relatedChain);
	
	public int insertOverTimeSaveDao(OverTimeDto overtimeDto);
	
	public OverTimeDto selectOverTimeSavedDao(Long acceptNo);
	
	public OverTimeDto selectOverTimeUpdateOfAcceptNoDao(Long acceptNo);
	
	public OverTimeDto selectOverTimeUpdateOfAcceptNoForAccessEmployeeNameDao(Long acceptNo);
	
	public int overTimeUpdateOfIsAccessLockDao(OverTimeDto overTimeDto);
	
	public int overTimeUpdateDao(OverTimeDto overTimeDto);
	
}
