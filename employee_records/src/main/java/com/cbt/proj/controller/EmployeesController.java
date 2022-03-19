package com.cbt.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import org.springframework.web.bind.annotation.RestController;

import com.cbt.proj.model.Employee;
import com.cbt.proj.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
	
	@Autowired
	EmployeeServiceImpl employeeServiceImpl;
	
	/** Create employee by API call */
	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmp(@RequestBody Employee emp){
		return new ResponseEntity<>(employeeServiceImpl.saveEmployee(emp), HttpStatus.CREATED);
	}
	
	/** Retrieve all employees  */
	@GetMapping("/getAll")
	public List<Employee> getAllEmployees(){
		return employeeServiceImpl.getAllEmployees();
	}
	
	/**Get employee by ID */
	@GetMapping("/getById")
	public Employee getEmployeeById(@RequestParam long id) {
		return employeeServiceImpl.getEmployeeById(id);
	}
	
	// http://localhost:8080/api/employees/2
	@GetMapping("/getByid/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable("id") long empId){
		return new ResponseEntity<Employee>(employeeServiceImpl.getEmployeeById(empId), HttpStatus.CREATED);
	}

}
