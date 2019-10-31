package com.springproject.department.dao;

import java.util.List;

import com.springproject.department.dto.DepartmentDto;

public interface DepartmentDao {

	List<DepartmentDto> selectSomeDepartmentDao(DepartmentDto departmentDto);
}
