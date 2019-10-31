package com.springproject.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.common.utils.SHA256Util;
import com.springproject.employee.dao.EmployeeDao;
import com.springproject.employee.dto.EmployeeDto;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public boolean insertOneEmployeeService(EmployeeDto employeeDto) {
		
		String salt = SHA256Util.generateSalt();
		String password = this.getHashedPassword(salt, employeeDto.getEmployeePassWord());
		
		employeeDto.setEmployeePassWord(password);
		employeeDto.setSalt(salt);
		
		return this.employeeDao.insertOneEmployeeDao(employeeDto) > 0;
	}
	
	public String getHashedPassword(String salt, String password) {
		return SHA256Util.getEncrypt(password, salt);
	}

	@Override
	public EmployeeDto selectOneEmployeeService(EmployeeDto employeeDto) {
		String salt = this.employeeDao.getSaltByEmployeeNumber(employeeDto.getEmployeeNo());
//		if ( salt != null ) {
//			String password = this.getHashedPassword(salt, employeeDto.getEmployeePassWord());
//			employeeDto.setEmployeePassWord(password);
//		}
		
		EmployeeDto loginEmployeeDto = this.employeeDao.selectOneEmployeeDao(employeeDto);
		return loginEmployeeDto;
	}

	@Override
	public int duplicateCheckOfEmployeeNoService(String employeeNo) {
		return this.employeeDao.duplicateCheckOfEmployeeNoDao(employeeNo);
	}
	
}
