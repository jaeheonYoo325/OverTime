package com.springproject.overtime.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

@Repository
public class OverTimeDaoImpl extends SqlSessionDaoSupport implements OverTimeDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	@Override
	public List<EmployeeDto> selectAllEmployeeDao() {
		return getSqlSession().selectList("overTimeDao.selectAllEmployeeDao");
	}

	@Override
	public List<EmployeeDto> selectEmployeeOfSearchDao(EmployeeDto employeeDto) {
		return getSqlSession().selectList("overTimeDao.selectEmployeeOfSearchDao",employeeDto);
	}

	@Override
	public List<InterPhoneDto> sellectAllInterPhoneDao() {
		return getSqlSession().selectList("overTimeDao.sellectAllInterPhoneDao");
	}

	@Override
	public List<InterPhoneDto> selectCallerOfSearchDao(InterPhoneDto interPhoneDto) {
		return getSqlSession().selectList("overTimeDao.selectCallerOfSearchDao",interPhoneDto);
	}

	@Override
	public List<ChainTableDto> selectAllChainDao() {
		return getSqlSession().selectList("overTimeDao.selectAllChainDao");
	}

	@Override
	public List<ChainTableDto> selectChainOfSearchDao(ChainTableDto chainTableDto) {
		return getSqlSession().selectList("overTimeDao.selectChainOfDao",chainTableDto);
	}
	@Override
	public int insertOverTimeRequestDao(OverTimeDto overtimeDto) {
		return getSqlSession().insert("overTimeDao.insertOverTimeRequestDao", overtimeDto);
	}
	@Override
	public Long selectMaxAcceptNoDao() {
		return getSqlSession().selectOne("overTimeDao.selectMaxAcceptNoDao");
	}
	@Override
	public int insertMeasurerDao(MeasurerDto measurerDto) {
		return getSqlSession().insert("overTimeDao.insertMeasurerDao",measurerDto);
	}
	@Override
	public int insertMeasureDescriptionDao(MeasureDescriptionDto measureDescriptionDto) {
		return getSqlSession().insert("overTimeDao.insertMeasureDescriptionDao",measureDescriptionDto);
	}
	@Override
	public int InsertOverTimeApprovalForOverTimeRequestDao(OverTimeApprovalDto overTimeApprovalDto) {
		return getSqlSession().insert("overTimeDao.InsertOverTimeApprovalForOverTimeRequestDao",overTimeApprovalDto);
	}
	@Override
	public List<MasterTableDto> selectMasterCodeOfCategoryDao() {
		return getSqlSession().selectList("overTimeDao.selectMasterCodeOfCategoryDao");
	}
	@Override
	public List<MasterTableDto> selectCategoryMasterCodesOfCodeTypeDao(String codeType) {
		return getSqlSession().selectList("overTimeDao.selectCategoryMasterCodesOfCodeTypeDao",codeType);
	}
	@Override
	public List<MasterTableDto> selectMasterCodeOfSearchTypeMapDao(String searchTypeString) {
		return getSqlSession().selectList("overTimeDao.selectMasterCodeOfSearchTypeMapDao",searchTypeString);
	}
	@Override
	public List<OverTimeDto> selectCategoryOverTimeRequestDao(CategoryTypeDto overTimeRequestForCategory) {
		return getSqlSession().selectList("overTimeDao.selectCategoryOverTimeRequestDao",overTimeRequestForCategory);
	}
	@Override
	public List<OverTimeDto> selectAllOverTimeRequestDao() {
		return getSqlSession().selectList("overTimeDao.selectAllOverTimeRequestDao");
	}
	@Override
	public List<MeasurerDto> selectMeasurerOfAcceptNoDao(Long acceptNo) {
		return getSqlSession().selectList("overTimeDao.selectMeasurerOfAcceptNoDao",acceptNo);
	}
	@Override
	public List<MeasureDescriptionDto> selectMeasureDescriptionOfAcceptNoDao(Long acceptNo) {
		return getSqlSession().selectList("overTimeDao.selectMeasureDescriptionOfAcceptNoDao",acceptNo);
	}
	@Override
	public OverTimeofEmployeeDto selectOverTimeofEmployeeDao(EmployeeDto employeeDto) {
		return getSqlSession().selectOne("overTimeDao.selectOverTimeofEmployeeDao",employeeDto);
	}
	@Override
	public OverTimeDto selectOverTimeRequestOfAcceptNoDao(Long acceptNo) {
		return getSqlSession().selectOne("overTimeDao.selectOverTimeRequestOfAcceptNoDao", acceptNo);
	}
	@Override
	public int updateOneOverTimeRequestDao(OverTimeDto overTimeDto) {
		return getSqlSession().update("overTimeDao.updateOneOverTimeRequestDao", overTimeDto);
	}
	@Override
	public int deleteMeasurerOfAcceptNoDao(Long acceptNo) {
		return getSqlSession().delete("overTimeDao.deleteMeasurerOfAcceptNoDao", acceptNo);
	}
	@Override
	public int deleteMeasureDescriptionOfAcceptNoDao(Long acceptNo) {
		return getSqlSession().delete("overTimeDao.deleteMeasureDescriptionOfAcceptNoDao", acceptNo);
	}
	@Override
	public int insertRelatedChainDao(RelatedChainDto relatedChainDto) {
		return getSqlSession().insert("overTimeDao.insertRelatedChainDao",relatedChainDto);
	}
	@Override
	public List<RelatedChainDto> selectRelatedChainOfAcceptNoDao(Long acceptNo) {
		return getSqlSession().selectList("overTimeDao.selectRelatedChainOfAcceptNoDao",acceptNo);
	}
	@Override
	public int deleteRelatedChainOfAcceptNoDao(Long acceptNo) {
		return getSqlSession().delete("overTimeDao.deleteRelatedChainOfAcceptNoDao", acceptNo);
	}
	@Override
	public List<InterPhoneDto> selectCallerOfSearchAjaxDao(String caller) {
		return getSqlSession().selectList("overTimeDao.selectCallerOfSearchAjaxDao", caller);
	}
	@Override
	public List<EmployeeDto> selectEmployeeOfSearchAjaxDao(String measurer) {
		return getSqlSession().selectList("overTimeDao.selectEmployeeOfSearchAjaxDao", measurer);
	}
	@Override
	public List<ChainTableDto> selectChainOfSearchAjaxDao(String relatedChain) {
		return getSqlSession().selectList("overTimeDao.selectChainOfSearchAjaxDao", relatedChain);
	}
	@Override
	public int insertOverTimeSaveDao(OverTimeDto overtimeDto) {
		return getSqlSession().insert("overTimeDao.insertOverTimeSaveDao", overtimeDto);
	}
	@Override
	public OverTimeDto selectOverTimeSavedDao(Long acceptNo) {
		return getSqlSession().selectOne("overTimeDao.selectOverTimeSavedDao", acceptNo);
	}
	@Override
	public OverTimeDto selectOverTimeUpdateOfAcceptNoDao(Long acceptNo) {
		return getSqlSession().selectOne("overTimeDao.selectOverTimeUpdateOfAcceptNoDao", acceptNo);
	}
	@Override
	public OverTimeDto selectOverTimeUpdateOfAcceptNoForAccessEmployeeNameDao(Long acceptNo) {
		return getSqlSession().selectOne("overTimeDao.selectOverTimeUpdateOfAcceptNoForAccessEmployeeNameDao", acceptNo);
	}
	@Override
	public int overTimeUpdateOfIsAccessLockDao(OverTimeDto overTimeDto) {
		return getSqlSession().update("overTimeDao.overTimeUpdateOfIsAccessLockDao", overTimeDto);
	}
	@Override
	public int overTimeUpdateDao(OverTimeDto overTimeDto) {
		return getSqlSession().update("overTimeDao.overTimeUpdateDao", overTimeDto);
	}

}
