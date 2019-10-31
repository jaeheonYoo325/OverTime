package com.springproject.department.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproject.department.dto.DepartmentDto;

@Repository
public class DepartmentDaoImpl extends SqlSessionDaoSupport implements DepartmentDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public List<DepartmentDto> selectSomeDepartmentDao(DepartmentDto departmentDto) {
		return getSqlSession().selectList("DepartmentDao.selectSomeDepartment", departmentDto);
	}

}
