package com.cbt.proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbt.proj.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
