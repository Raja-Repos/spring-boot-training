package com.cbt.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import org.springframework.web.bind.annotation.RestController;

import com.cbt.proj.model.AuthRequest;
import com.cbt.proj.model.AuthResponse;
import com.cbt.proj.model.Employee;
import com.cbt.proj.security.MyUserDetailService;
import com.cbt.proj.service.EmployeeServiceImpl;
import com.cbt.proj.utils.JwtUtils;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
	
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private MyUserDetailService myUserDetails;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	/** Authentication purpose */
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) throws Exception{
		
		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		}
		catch(BadCredentialsException e) {
			System.out.println("Given username or password is wrong..");
		}
		
		final UserDetails userDetails = myUserDetails.loadUserByUsername(authRequest.getUserName());
		
		String jwt = jwtUtils.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthResponse(jwt));
	}
	
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
	// http://localhost:8080/api/employees?id=2
	@GetMapping("/getById")
	public Employee getEmployeeById(@RequestParam long id) {
		return employeeServiceImpl.getEmployeeById(id);
	}
	
	// http://localhost:8080/api/employees/2
	@GetMapping("/getByid/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable("id") long empId){
		return new ResponseEntity<>(employeeServiceImpl.getEmployeeById(empId), HttpStatus.CREATED);
	}
	
	/** Update employee API call */
	@PutMapping("/updateById/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee emp){
		return new ResponseEntity<>(employeeServiceImpl.updateEmployee(emp, id), HttpStatus.OK);
	}
	/** Delete employee API call */
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		employeeServiceImpl.deleteEmployee(id);
		return new ResponseEntity<>("Deleted successfully!!",HttpStatus.OK);
	}

}
