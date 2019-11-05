package com.springproject.overtime.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproject.dtos.ChainTableDto;
import com.springproject.dtos.InterPhoneDto;
import com.springproject.dtos.MeasureDescriptionDto;
import com.springproject.dtos.MeasurerDto;
import com.springproject.dtos.OverTimeApprovalDto;
import com.springproject.dtos.OverTimeDto;
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
}
