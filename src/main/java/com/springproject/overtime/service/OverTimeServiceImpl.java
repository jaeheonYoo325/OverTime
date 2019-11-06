package com.springproject.overtime.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.dtos.CategoryTypeDto;
import com.springproject.dtos.ChainTableDto;
import com.springproject.dtos.InterPhoneDto;
import com.springproject.dtos.MasterTableDto;
import com.springproject.dtos.MeasureDescriptionDto;
import com.springproject.dtos.MeasurerDto;
import com.springproject.dtos.OverTimeApprovalDto;
import com.springproject.dtos.OverTimeDto;
import com.springproject.dtos.OverTimeofEmployeeDto;
import com.springproject.employee.dto.EmployeeDto;
import com.springproject.overtime.dao.OverTimeDao;

@Service
@Transactional
public class OverTimeServiceImpl implements OverTimeService {
	
	@Autowired
	private OverTimeDao overTimeDao;

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
	public boolean insertOverTimeRequestService(OverTimeDto overtimeDto, ArrayList<String> measurer,ArrayList<String> measureDescription) {
		boolean isInsertOverTimeRequestSuccess=this.overTimeDao.insertOverTimeRequestDao(overtimeDto)>0;
		boolean isInsertMeasurerSuccess=true;
		boolean isInsertMeasureDescriptionSuccess=true;
		
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
			measureDescriptionDto.setMeasureDescription(measureDescription.get(i).toString());
			isInsertMeasureDescriptionSuccess=isInsertMeasureDescriptionSuccess&this.overTimeDao.insertMeasureDescriptionDao(measureDescriptionDto)>0;
		}
		
		OverTimeApprovalDto overTimeApprovalDto=new OverTimeApprovalDto();
		overTimeApprovalDto.setAcceptNo(acceptNo);
		overTimeApprovalDto.setDrafter(overtimeDto.getAccepter());
		overTimeApprovalDto.setApprovalLine("3");
		overTimeApprovalDto.setApprovalDescription("overTimeApprovalB0");
		boolean isInsertOverTimeApprovalForOverTimeRequestSuccess=this.overTimeDao.InsertOverTimeApprovalForOverTimeRequestDao(overTimeApprovalDto)>0;
		
		boolean isRequestSuccess=isInsertOverTimeRequestSuccess&isInsertMeasurerSuccess&isInsertMeasureDescriptionSuccess&isInsertOverTimeApprovalForOverTimeRequestSuccess;
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
}
