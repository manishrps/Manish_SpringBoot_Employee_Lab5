package com.gl.Employee_Management_System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.Employee_Management_System.dao.EmployeeRepository;
import com.gl.Employee_Management_System.entity.Employee;

@Service
public class EmployeeServiceImple implements EmployeeService {

	EmployeeRepository employeeRepository;

	public EmployeeServiceImple(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getAllEmployee() {
		System.out.println("got into serviceimpl get all employee");
		System.out.println(employeeRepository.findAll());
		return employeeRepository.findAll();
	}

	@Override
	public Employee saveEmployee(Employee theEmployee) {
		return employeeRepository.save(theEmployee);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Employee theEmployee = employeeRepository.findById(id).get();
		if (theEmployee == null) {
			throw new RuntimeException("did not find employee id" + id);
		} else {
			return theEmployee;
		}
	}

	@Override
	public Employee updateEmployee(Employee theEmployee) {

		return employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);
	}

}
