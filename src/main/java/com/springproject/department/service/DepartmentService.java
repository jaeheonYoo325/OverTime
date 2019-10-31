package com.springproject.department.service;

import java.util.List;

import com.springproject.department.dto.DepartmentDto;

public interface DepartmentService {

	List<DepartmentDto> selectSomeDepartmentService(DepartmentDto departmentDto);
}
