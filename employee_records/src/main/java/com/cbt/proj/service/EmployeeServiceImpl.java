package com.cbt.proj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.proj.model.Employee;
import com.cbt.proj.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Override
	public Employee saveEmployee(Employee emp) {
		return employeeRepo.save(emp);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> emp = employeeRepo.findById(id);
		if(emp.isPresent()) {
			return emp.get();
		}
		else {
			throw new RuntimeException();
		}
		//return employeeRepo.findById(id).orElseThrow();
	}

}
