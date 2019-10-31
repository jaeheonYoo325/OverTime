package com.springproject.department.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.department.dao.DepartmentDao;
import com.springproject.department.dto.DepartmentDto;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public List<DepartmentDto> selectSomeDepartmentService(DepartmentDto departmentDto) {
		return this.departmentDao.selectSomeDepartmentDao(departmentDto);
	}
	
}
