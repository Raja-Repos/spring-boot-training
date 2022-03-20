package com.cbt.proj.service;

import com.cbt.proj.model.Employee;
import java.util.*;

public interface EmployeeService {
	
	Employee saveEmployee(Employee emp);
	
	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(long id); 
	
	Employee updateEmployee(Employee emp, long id);
	
	void deleteEmployee(long id);

}
