package com.springproject.employee.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproject.dtos.AuthorityDto;
import com.springproject.dtos.MeasurerDto;
import com.springproject.dtos.OverTimeApprovalDto;
import com.springproject.dtos.OverTimeDto;
import com.springproject.dtos.OverTimeofEmployeeDto;
import com.springproject.employee.dto.EmployeeDto;
import com.springproject.overtimeAPI.ElementsOfOverTimeForCalculate;

@Repository
public class EmployeeDaoImpl extends SqlSessionDaoSupport implements EmployeeDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public int insertOneEmployeeDao(EmployeeDto employeeDto) {
		return getSqlSession().insert("EmployeeDao.insertOneEmployeeDao", employeeDto);
	}

	@Override
	public EmployeeDto selectOneEmployeeDao(EmployeeDto employeeDto) {
		return getSqlSession().selectOne("EmployeeDao.selectOneEmployeeDao", employeeDto);
	}

	@Override
	public String getSaltByEmployeeNumber(String empNo) {
		return getSqlSession().selectOne("EmployeeDao.getSaltByEmployeeNumber", empNo);
	}

	@Override
	public int duplicateCheckOfEmployeeNoDao(String employeeNo) {
		return this.getSqlSession().selectOne("EmployeeDao.duplicateCheckOfEmployeeNoDao", employeeNo);
	}

	@Override
	public int isInsertOneOvertimeOfEmployeeDao(EmployeeDto employeeDto) {
		return this.getSqlSession().insert("EmployeeDao.isInsertOneOvertimeOfEmployeeDao",employeeDto);
	}

	@Override
	public List<OverTimeApprovalDto> selectMyOverTimeApprovalDao(EmployeeDto employeeDto) {
		return this.getSqlSession().selectList("EmployeeDao.selectMyOverTimeApprovalDao", employeeDto);
	}

	@Override
	public OverTimeApprovalDto selectMyOverTimeApprovalOfAcceptNoDao(Long acceptNo) {
		return this.getSqlSession().selectOne("EmployeeDao.selectMyOverTimeApprovalOfAcceptNoDao", acceptNo);
	}

	@Override
	public int myOverTimeDoApprovalingOfCompleteNowApprovalDao(OverTimeApprovalDto overTimeApprovalDto) {
		return this.getSqlSession().update("EmployeeDao.myOverTimeDoApprovalingOfCompleteNowApprovalDao", overTimeApprovalDto);
	}

	@Override
	public int myOverTimeDoApprovalingOfAddNextApprovalDao(OverTimeApprovalDto overTimeApprovalDto) {
		return this.getSqlSession().insert("EmployeeDao.myOverTimeDoApprovalingOfAddNextApprovalDao", overTimeApprovalDto);
	}

	@Override
	public int changeStatusCodeForOverTimeDoApprovalingDao(OverTimeDto overTimeDto) {
		return this.getSqlSession().update("EmployeeDao.changeStatusCodeForOverTimeDoApprovalingDao", overTimeDto);
	}

	@Override
	public List<OverTimeApprovalDto> selectMyOverTimeApprovedDao(EmployeeDto employeeDto) {
		return this.getSqlSession().selectList("EmployeeDao.selectMyOverTimeApprovedDao", employeeDto);
	}

	@Override
	public OverTimeofEmployeeDto selectOverTimeOfEmployeeNoDao(String employeeNo) {
		return this.getSqlSession().selectOne("EmployeeDao.selectOverTimeOfEmployeeNoDao", employeeNo);
	}

	@Override
	public List<OverTimeApprovalDto> selectMyOverTimeCompletedDao(EmployeeDto employeeDto) {
		return this.getSqlSession().selectList("EmployeeDao.selectMyOverTimeCompletedDao", employeeDto);
	}

	@Override
	public int myDeployDoReturningOfNextApprovalDao(OverTimeApprovalDto overTimeApprovalDto) {
		return this.getSqlSession().insert("EmployeeDao.myDeployDoReturningOfNextApprovalDao", overTimeApprovalDto);
	}

	@Override
	public List<OverTimeApprovalDto> selectMyOverTimeReturnedDao(EmployeeDto employeeDto) {
		return this.getSqlSession().selectList("EmployeeDao.selectMyOverTimeReturnedDao", employeeDto);
	}

	@Override
	public List<MeasurerDto> measurerListOfAcceptNoDao(Long acceptNo) {
		return this.getSqlSession().selectList("EmployeeDao.measurerListOfAcceptNoDao", acceptNo);
	}

	@Override
	public int measurerOfMeasureTimeAccumulationDao(ElementsOfOverTimeForCalculate elementsOfOverTimeForCalculate) {
		return this.getSqlSession().update("EmployeeDao.measurerOfMeasureTimeAccumulationDao", elementsOfOverTimeForCalculate);
	}

	@Override
	public List<AuthorityDto> checkIsThisUserHaveRequestOfOverTimeAuthorityDao(EmployeeDto employeeDto) {
		 return this.getSqlSession().selectList("EmployeeDao.checkIsThisUserHaveRequestOfOverTimeAuthorityDao",employeeDto);

	}

	@Override
	public List<AuthorityDto> checkisThisUserHaveAuthorityOfEmployeeRegistDao(EmployeeDto employeeDto) {
		return this.getSqlSession().selectList("EmployeeDao.checkisThisUserHaveAuthorityOfEmployeeRegistDao",employeeDto);
	}
	
	
}
