package com.springproject.overtime.service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhncorp.lucy.security.xss.XssFilter;
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
import com.springproject.employee.dao.EmployeeDao;
import com.springproject.employee.dto.EmployeeDto;
import com.springproject.overtime.dao.OverTimeDao;

@Service
@Transactional
public class OverTimeServiceImpl implements OverTimeService {
	
	@Autowired
	private OverTimeDao overTimeDao;
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<EmployeeDto> selectAllEmployeeService() {
		return this.overTimeDao.selectAllEmployeeDao();
	}

	@Override
	public List<EmployeeDto> selectEmployeeOfSearchService(EmployeeDto employeeDto) {
		return this.overTimeDao.selectEmployeeOfSearchDao(employeeDto);
	}

	@Override
	public List<InterPhoneDto> sellectAllInterPhoneService() {
		return this.overTimeDao.sellectAllInterPhoneDao();
	}

	@Override
	public List<InterPhoneDto> selectCallerOfSearchService(InterPhoneDto interPhoneDto) {
		return this.overTimeDao.selectCallerOfSearchDao(interPhoneDto);
	}

	@Override
	public List<ChainTableDto> selectAllChainService() {
		return this.overTimeDao.selectAllChainDao();
	}

	@Override
	public List<ChainTableDto> selectChainOfSearchService(ChainTableDto chainTableDto) {
		return this.overTimeDao.selectChainOfSearchDao(chainTableDto);
	}

	@Override
	public boolean insertOverTimeRequestService(OverTimeDto overtimeDto, ArrayList<String> measurer,ArrayList<String> measureDescription, ArrayList<String> relatedChain) {
		boolean isInsertMeasurerSuccess=true;
		boolean isInsertMeasureDescriptionSuccess=true;
		boolean isInsertRelatedChainSuccess=true;
		
		// XSS 방어로직 구현
		XssFilter xssFilter = XssFilter.getInstance("xssfilter/lucy-xss-superset.xml", true);
		overtimeDto.setAcceptDescription(xssFilter.doFilter(overtimeDto.getAcceptDescription()));		
		overtimeDto.setCause(xssFilter.doFilter(overtimeDto.getCause()));
		overtimeDto.setMeasures(xssFilter.doFilter(overtimeDto.getMeasures()));
		overtimeDto.setRemarks(xssFilter.doFilter(overtimeDto.getRemarks()));
		overtimeDto.setTypeOfProcessing(xssFilter.doFilter(overtimeDto.getTypeOfProcessing()));
		
		boolean isInsertOverTimeRequestSuccess = this.overTimeDao.insertOverTimeRequestDao(overtimeDto) > 0;

		
		Long acceptNo=this.overTimeDao.selectMaxAcceptNoDao();
		
		for(int i=0; i<measurer.size();i++) {
			MeasurerDto measurerDto=new MeasurerDto();
			measurerDto.setAcceptNo(acceptNo);
			measurerDto.setMeasurer(measurer.get(i).toString());
			isInsertMeasurerSuccess=isInsertMeasurerSuccess&this.overTimeDao.insertMeasurerDao(measurerDto)>0;
		}
		
		for(int i=0; i<measureDescription.size(); i++) {
			MeasureDescriptionDto measureDescriptionDto=new MeasureDescriptionDto();
			measureDescriptionDto.setAcceptNo(acceptNo);
			measureDescriptionDto.setMeasureDescription(xssFilter.doFilter(measureDescription.get(i).toString()));
//			measureDescriptionDto.setMeasureDescription(measureDescription.get(i).toString());
			isInsertMeasureDescriptionSuccess=isInsertMeasureDescriptionSuccess&this.overTimeDao.insertMeasureDescriptionDao(measureDescriptionDto)>0;
		}
		
		for(int i=0; i<relatedChain.size(); i++) {
			RelatedChainDto relatedChainDto=new RelatedChainDto();
			relatedChainDto.setAcceptNo(acceptNo);
			relatedChainDto.setRelatedChain(relatedChain.get(i).toString());
			isInsertRelatedChainSuccess=isInsertRelatedChainSuccess&&this.overTimeDao.insertRelatedChainDao(relatedChainDto)>0;
		}
		
		OverTimeApprovalDto overTimeApprovalDto=new OverTimeApprovalDto();
		overTimeApprovalDto.setAcceptNo(acceptNo);
		overTimeApprovalDto.setDrafter(overtimeDto.getAccepter());
		overTimeApprovalDto.setApprovalLine("1");
		overTimeApprovalDto.setApprovalDescription("overTimeApprovalD0");
		boolean isInsertOverTimeApprovalForOverTimeRequestSuccess=this.overTimeDao.InsertOverTimeApprovalForOverTimeRequestDao(overTimeApprovalDto)>0;
		
		boolean isRequestSuccess=isInsertOverTimeRequestSuccess&isInsertMeasurerSuccess&isInsertMeasureDescriptionSuccess&isInsertOverTimeApprovalForOverTimeRequestSuccess&isInsertRelatedChainSuccess;
		return isRequestSuccess;
	}

	@Override
	public List<MasterTableDto> selectMasterCodeOfCategoryService() {
		return this.overTimeDao.selectMasterCodeOfCategoryDao();
	}

	@Override
	public Map<String, List<MasterTableDto>> selectCategoryMasterCodesOfCodeTypeService(List<MasterTableDto> masterCodeOfCategory) {
		List<MasterTableDto> categoryOneMasterCode=new ArrayList<MasterTableDto>();
		Map<String,List<MasterTableDto>> masterData=new HashMap<String, List<MasterTableDto>>();
		
		for(int i=0; i<masterCodeOfCategory.size(); i++) {
			categoryOneMasterCode = this.overTimeDao.selectCategoryMasterCodesOfCodeTypeDao(masterCodeOfCategory.get(i).getCodeType());
			masterData.put(masterCodeOfCategory.get(i).getCodeType(), categoryOneMasterCode);
		}
		return masterData;
	}

	@Override
	public Map<String, List<MasterTableDto>> selectMasterCodeOfSearchTypeMapService(String searchTypeString) {
		List<MasterTableDto> searchTypeOneMasterCode = new ArrayList<MasterTableDto>();
		Map<String, List<MasterTableDto>> masterData = new HashMap<String, List<MasterTableDto>>();
		
		searchTypeOneMasterCode = this.overTimeDao.selectMasterCodeOfSearchTypeMapDao(searchTypeString);
		masterData.put(searchTypeString, searchTypeOneMasterCode);
		
		return masterData;
	}

	@Override
	public List<OverTimeDto> selectCategoryOverTimeRequestService(CategoryTypeDto categoryTypeDto) {
		CategoryTypeDto OverTimeRequestForCategory = new CategoryTypeDto();
		if (categoryTypeDto.getSearchType().equals("검색타입")) {
			OverTimeRequestForCategory.setSearchType("%");
		} else {
			OverTimeRequestForCategory.setSearchType(categoryTypeDto.getSearchType());
		}
		
		if (categoryTypeDto.getSearchKeyword().equals("") ) {
			OverTimeRequestForCategory.setSearchKeyword("%");
		} else {
			OverTimeRequestForCategory.setSearchKeyword("%"+categoryTypeDto.getSearchKeyword());
		}
		
		if (categoryTypeDto.getCategoryChain().equals("관련체인")) {
			OverTimeRequestForCategory.setCategoryChain("%");
		} else {
			OverTimeRequestForCategory.setCategoryChain(categoryTypeDto.getCategoryChain());
		}
		
		if (categoryTypeDto.getCategoryStatus().equals("00")) {
			OverTimeRequestForCategory.setCategoryStatus("%");
		} else {
			OverTimeRequestForCategory.setCategoryStatus(categoryTypeDto.getCategoryStatus());
		}
		
		OverTimeRequestForCategory.setCategoryAcceptDate(categoryTypeDto.getCategoryAcceptDate());
		return this.overTimeDao.selectCategoryOverTimeRequestDao(OverTimeRequestForCategory);
	}

	@Override
	public List<OverTimeDto> selectAllOverTimeRequestService() {
		return this.overTimeDao.selectAllOverTimeRequestDao();
	}

	@Override
	public List<MeasurerDto> selectMeasurerOfAcceptNoService(Long acceptNo) {
		return this.overTimeDao.selectMeasurerOfAcceptNoDao(acceptNo);
	}

	@Override
	public List<MeasureDescriptionDto> selectMeasureDescriptionOfAcceptNoService(Long acceptNo) {
		return this.overTimeDao.selectMeasureDescriptionOfAcceptNoDao(acceptNo);
	}

	@Override
	public OverTimeofEmployeeDto selectOverTimeofEmployeeService(EmployeeDto employeeDto) {
		return this.overTimeDao.selectOverTimeofEmployeeDao(employeeDto);
	}

	@Override
	public OverTimeDto selectOverTimeRequestOfAcceptNoService(Long acceptNo) {
		return this.overTimeDao.selectOverTimeRequestOfAcceptNoDao(acceptNo);
	}

	@Override
	public boolean overTimeReRequestService(OverTimeDto overTimeDto, ArrayList<String> measurer, ArrayList<String> measureDescription, ArrayList<String> relatedChain) {
		
		Long acceptNo = overTimeDto.getAcceptNo();
		
		boolean isOverTimeReRequestFinalSuccess = true;
		boolean insertMeasurerSuccess = true;
		boolean insertMeasureDescriptionSuccess = true;	
		boolean insertRelatedChainSuccess = true;
		
		// XSS 방어로직 구현
		XssFilter xssFilter = XssFilter.getInstance("xssfilter/lucy-xss-superset.xml", true);
		overTimeDto.setAcceptDescription(xssFilter.doFilter(overTimeDto.getAcceptDescription()));		
		overTimeDto.setCause(xssFilter.doFilter(overTimeDto.getCause()));
		overTimeDto.setMeasures(xssFilter.doFilter(overTimeDto.getMeasures()));
		overTimeDto.setRemarks(xssFilter.doFilter(overTimeDto.getRemarks()));
		overTimeDto.setTypeOfProcessing(xssFilter.doFilter(overTimeDto.getTypeOfProcessing()));
		
		overTimeDto.setStatusCode("01");
		boolean isOverTimeReRequestSuccess = this.overTimeDao.updateOneOverTimeRequestDao(overTimeDto) > 0;
		boolean isDeleteMeasurerOfAcceptNoSuccess = this.overTimeDao.deleteMeasurerOfAcceptNoDao(acceptNo) > 0;
		boolean isDeleteMeasureDescriptionOfAcceptNoSuccess = this.overTimeDao.deleteMeasureDescriptionOfAcceptNoDao(acceptNo) > 0;
		boolean isDeleteRelatedChainOfAcceptNoSuccess = this.overTimeDao.deleteRelatedChainOfAcceptNoDao(acceptNo)>0;
		
		for(int i = 0; i < measurer.size(); i++) {
			MeasurerDto measurerDto = new MeasurerDto();
			measurerDto.setAcceptNo(acceptNo);
			measurerDto.setMeasurer(measurer.get(i).toString());
			insertMeasurerSuccess = insertMeasurerSuccess && ( this.overTimeDao.insertMeasurerDao(measurerDto) > 0);
		}
		
		for(int i = 0; i < measureDescription.size(); i++) {
			MeasureDescriptionDto measureDescriptionDto = new MeasureDescriptionDto();
			measureDescriptionDto.setAcceptNo(acceptNo);
			measureDescriptionDto.setMeasureDescription(xssFilter.doFilter(measureDescription.get(i).toString()));
//			measureDescriptionDto.setMeasureDescription(measureDescription.get(i).toString());
			insertMeasureDescriptionSuccess = insertMeasureDescriptionSuccess && ( this.overTimeDao.insertMeasureDescriptionDao(measureDescriptionDto) > 0);
		}
		
		for(int i=0; i<relatedChain.size(); i++) {
			RelatedChainDto relatedChainDto=new RelatedChainDto();
			relatedChainDto.setAcceptNo(acceptNo);
			relatedChainDto.setRelatedChain(relatedChain.get(i).toString());
			insertRelatedChainSuccess=insertRelatedChainSuccess&&(this.overTimeDao.insertRelatedChainDao(relatedChainDto)>0);
		}
		
		OverTimeApprovalDto overTimeApprovalDto = this.employeeDao.selectMyOverTimeApprovalOfAcceptNoDao(acceptNo);
		overTimeApprovalDto.setApprovalLineConfirm(overTimeDto.getAccepter());
		boolean isDoApprovalingSuccessOfCompleteNowApproval = this.employeeDao.myOverTimeDoApprovalingOfCompleteNowApprovalDao(overTimeApprovalDto) > 0;
		overTimeApprovalDto.setApprovalLine("1");
		overTimeApprovalDto.setApprovalDescription("overTimeApprovalD0");
		boolean isDoApprovalingSuccessOfNextApproval = this.employeeDao.myOverTimeDoApprovalingOfAddNextApprovalDao(overTimeApprovalDto) > 0;
		
		isOverTimeReRequestFinalSuccess = isOverTimeReRequestFinalSuccess && isOverTimeReRequestSuccess && isDeleteMeasurerOfAcceptNoSuccess && isDeleteMeasureDescriptionOfAcceptNoSuccess 
				&& insertMeasurerSuccess && insertMeasureDescriptionSuccess && isDoApprovalingSuccessOfCompleteNowApproval && isDoApprovalingSuccessOfNextApproval&&insertRelatedChainSuccess&&isDeleteRelatedChainOfAcceptNoSuccess;
		return isOverTimeReRequestFinalSuccess;
	}

	@Override
	public List<RelatedChainDto> selectRelatedChainOfAcceptNoService(Long acceptNo) {
		return this.overTimeDao.selectRelatedChainOfAcceptNoDao(acceptNo);
	}

	@Override
	public List<InterPhoneDto> selectCallerOfSearchAjaxService(String caller) {
		return this.overTimeDao.selectCallerOfSearchAjaxDao(caller);
	}

	@Override
	public List<EmployeeDto> selectEmployeeOfSearchAjaxService(String measurer) {
		return this.overTimeDao.selectEmployeeOfSearchAjaxDao(measurer);
	}

	@Override
	public List<ChainTableDto> selectChainOfSearchAjaxService(String relatedChain) {
		return this.overTimeDao.selectChainOfSearchAjaxDao(relatedChain);
	}

}
