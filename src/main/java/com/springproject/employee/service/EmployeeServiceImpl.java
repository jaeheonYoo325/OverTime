package com.springproject.employee.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.common.utils.SHA256Util;
import com.springproject.dtos.AuthorityDto;
import com.springproject.dtos.MeasurerDto;
import com.springproject.dtos.OverTimeApprovalDto;
import com.springproject.dtos.OverTimeDto;
import com.springproject.employee.dao.EmployeeDao;
import com.springproject.employee.dto.EmployeeDto;
import com.springproject.overtime.dao.OverTimeDao;
import com.springproject.overtimeAPI.ElementsOfOverTimeForCalculate;
import com.springproject.overtimeAPI.OverTimeCalculater;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private OverTimeDao overTimeDao;
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public boolean insertOneEmployeeService(EmployeeDto employeeDto) {
		
		String salt = SHA256Util.generateSalt();
		String password = this.getHashedPassword(salt, employeeDto.getEmployeePassWord());
		
		employeeDto.setEmployeePassWord(password);
		employeeDto.setSalt(salt);
		boolean isInsertOneEmployeeSuccess=this.employeeDao.insertOneEmployeeDao(employeeDto) > 0;
		boolean isInsertOneOvertimeofemployee=this.employeeDao.isInsertOneOvertimeOfEmployeeDao(employeeDto)>0;
		
		return isInsertOneEmployeeSuccess&isInsertOneOvertimeofemployee;
	}
	
	public String getHashedPassword(String salt, String password) {
		return SHA256Util.getEncrypt(password, salt);
	}

	@Override
	public EmployeeDto selectOneEmployeeService(EmployeeDto employeeDto) {
		String salt = this.employeeDao.getSaltByEmployeeNumber(employeeDto.getEmployeeNo());
		
		if ( salt != null ) {
			String password = this.getHashedPassword(salt, employeeDto.getEmployeePassWord());
			employeeDto.setEmployeePassWord(password);
		}
		
		EmployeeDto loginEmployeeDto = this.employeeDao.selectOneEmployeeDao(employeeDto);
		return loginEmployeeDto;
	}

	@Override
	public int duplicateCheckOfEmployeeNoService(String employeeNo) {
		return this.employeeDao.duplicateCheckOfEmployeeNoDao(employeeNo);
	}

	@Override
	public List<OverTimeApprovalDto> selectMyOverTimeApprovalService(EmployeeDto employeeDto) {
		return this.employeeDao.selectMyOverTimeApprovalDao(employeeDto);
	}

	@Override
	public OverTimeApprovalDto selectMyOverTimeApprovalOfAcceptNoService(Long acceptNo) {
		return this.employeeDao.selectMyOverTimeApprovalOfAcceptNoDao(acceptNo);
	}

	@Override
	public boolean myOverTimeDoApprovalingService(OverTimeApprovalDto overTimeApprovalDto, String measureTimeForApproval) {
		boolean isDoApprovalingSuccess = true;
		boolean isDoApprovalingSuccessOfCompleteNowApproval = false;
		boolean isMeasurerOfMeasureTimeAccumulationSuccess = true;
		
		isDoApprovalingSuccessOfCompleteNowApproval = this.employeeDao.myOverTimeDoApprovaling(overTimeApprovalDto) > 0;
		
		OverTimeDto overTimeDto = this.overTimeDao.selectOverTimeRequestOfAcceptNoDao(overTimeApprovalDto.getAcceptNo());
		
		if ( overTimeDto.getStatusCode().equals("01") ) {
			overTimeDto.setStatusCode("02");
			}
		
		overTimeDto.setMeasureTime(measureTimeForApproval);
		
		boolean isChangeOverTimeRequestStatusCodeSuccess = this.employeeDao.changeStatusCodeForOverTimeDoApprovalingDao(overTimeDto) > 0;

		// 최종 결재 승인 시 조치 시간 누적
			Long acceptNo = overTimeApprovalDto.getAcceptNo();
			List<MeasurerDto> measurerListOfAcceptNo = this.employeeDao.measurerListOfAcceptNoDao(acceptNo);
			
			for ( int i = 0; i < measurerListOfAcceptNo.size(); i++) {
				String employeeNo=measurerListOfAcceptNo.get(i).getMeasurer();
				String acceptDate=overTimeDto.getAcceptDate();
				String acceptTime=overTimeDto.getAcceptTime();
				String measureTime=overTimeDto.getMeasureTime();
				
				ElementsOfOverTimeForCalculate elementsOfOverTimeForCalculate=new ElementsOfOverTimeForCalculate(employeeNo, acceptDate, acceptTime, measureTime);
				OverTimeCalculater overTimeCalculater=OverTimeCalculater.getInstance();
				overTimeCalculater.calculateOverTime(elementsOfOverTimeForCalculate);;
				
				isMeasurerOfMeasureTimeAccumulationSuccess =  this.employeeDao.measurerOfMeasureTimeAccumulationDao(elementsOfOverTimeForCalculate) > 0;
			}	
		
		isDoApprovalingSuccess = isDoApprovalingSuccess && isDoApprovalingSuccessOfCompleteNowApproval && isChangeOverTimeRequestStatusCodeSuccess && isMeasurerOfMeasureTimeAccumulationSuccess;
		
		return isDoApprovalingSuccess;
	}

	@Override
	public List<OverTimeApprovalDto> selectMyOverTimeApprovedService(EmployeeDto employeeDto) {
		return this.employeeDao.selectMyOverTimeApprovedDao(employeeDto);
	}

	@Override
	public List<OverTimeApprovalDto> selectMyOverTimeCompletedService(EmployeeDto employeeDto) {
		return this.employeeDao.selectMyOverTimeCompletedDao(employeeDto);
	}

	@Override
	public boolean myOverTimeDoReturningService(OverTimeApprovalDto overTimeApprovalDto) {
		
		boolean isDoReturningSuccess = true;
		overTimeApprovalDto.setApprovalDescription("overTimeApprovalA1");
		boolean isDoReturningSuccessOfNowApprovalForReturn = this.employeeDao.myOverTimeDoApprovalingOfCompleteNowApprovalDao(overTimeApprovalDto) > 0;
		
		
		OverTimeDto overTimeRequestDto = this.overTimeDao.selectOverTimeRequestOfAcceptNoDao(overTimeApprovalDto.getAcceptNo());
		overTimeRequestDto.setStatusCode("03");
		boolean isChangeOverTimeRequestStatusCodeSuccess = this.employeeDao.changeStatusCodeForOverTimeDoApprovalingDao(overTimeRequestDto) > 0;
		
		isDoReturningSuccess = isDoReturningSuccess && isDoReturningSuccessOfNowApprovalForReturn && isChangeOverTimeRequestStatusCodeSuccess;
		return isDoReturningSuccess;
	}

	@Override
	public List<OverTimeApprovalDto> selectMyOverTimeReturnedService(EmployeeDto employeeDto) {
		return this.employeeDao.selectMyOverTimeReturnedDao(employeeDto);
	}

	@Override
	public boolean checkIsThisUserHaveRequestOfOverTimeAuthorityService(EmployeeDto employeeDto) {
		List<AuthorityDto> authorityDto= this.employeeDao.checkIsThisUserHaveRequestOfOverTimeAuthorityDao(employeeDto);
		if(authorityDto.size()>0) {
			return true;
		}
		else 
			return false;
	}

	@Override
	public boolean checkisThisUserHaveAuthorityOfEmployeeRegistService(EmployeeDto employeeDto) {
		List<AuthorityDto> authorityDto= this.employeeDao.checkisThisUserHaveAuthorityOfEmployeeRegistDao(employeeDto);
		if(authorityDto.size()>0) {
			return true;
		}
		else 
			return false;
	}

	@Override
	public List<MeasurerDto> selectMeasurerService(EmployeeDto employeeDto) {
		return this.employeeDao.selectMeasurerDao(employeeDto);
	}
	
}
