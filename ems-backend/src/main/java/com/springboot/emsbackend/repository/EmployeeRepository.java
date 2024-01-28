package com.springboot.emsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.emsbackend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
