package com.springboot.emsbackend.service;

import java.util.List;

import com.springboot.emsbackend.dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	List<EmployeeDto> getAllEmployee();
	
	EmployeeDto getEmployeById(Long employeeId);
	
	EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);
	
	void deleteEmployee(Long employeeId);
}
