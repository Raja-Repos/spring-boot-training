package com.cbt.proj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.proj.exception.ResourceNotFoundException;
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
		return employeeRepo.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Employee", "ID", id));
	}

	@Override
	public Employee updateEmployee(Employee emp, long id) {
		Employee e = employeeRepo.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Employee", "ID", id));
		
		e.setFirstName(emp.getFirstName());
		e.setLastName(emp.getLastName());
		e.setBranch(emp.getBranch());
		
		employeeRepo.save(e);
		
		return e;
	}

	@Override
	public void deleteEmployee(long id) {
		employeeRepo.findById(id).orElseThrow(() ->
		 new ResourceNotFoundException("Employee", "ID", id));
		employeeRepo.deleteById(id);
	}

}
