package com.springboot.emsbackend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.emsbackend.dto.EmployeeDto;
import com.springboot.emsbackend.entity.Employee;
import com.springboot.emsbackend.exception.ResourceNotFoundException;
import com.springboot.emsbackend.mapper.EmployeeMapper;
import com.springboot.emsbackend.repository.EmployeeRepository;
import com.springboot.emsbackend.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmployee = this.employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
		List<Employee> employees = this.employeeRepository.findAll();
		return employees.stream().map( (employee) ->  EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto getEmployeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
			.orElseThrow( () -> new ResourceNotFoundException("Employee is not exist with given Id " + employeeId));
		
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
		Employee employee = employeeRepository.findById(employeeId)
		.orElseThrow( () -> new ResourceNotFoundException("Employee is not exist with given Id " + employeeId));
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmail(employeeDto.getEmail());
		Employee savedEmployee = employeeRepository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
		.orElseThrow( () -> new ResourceNotFoundException("Employee is not exist with given Id " + employeeId));
		employeeRepository.deleteById(employeeId);
		
	}

}
