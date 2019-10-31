package com.springproject.employee.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproject.employee.dto.EmployeeDto;

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
	
	
}
