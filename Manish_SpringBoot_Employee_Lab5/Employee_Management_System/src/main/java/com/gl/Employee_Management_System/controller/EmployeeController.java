package com.gl.Employee_Management_System.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;

import com.gl.Employee_Management_System.entity.Employee;
import com.gl.Employee_Management_System.service.EmployeeService;

@Controller
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	// handler method to handle list employees and return mode and view
	@GetMapping("/employees")
	public String listemployees(Model theModel) {
		theModel.addAttribute("employees", employeeService.getAllEmployee());
		System.out.println("Got into the controller");
		return "employee-form";
	}

	@GetMapping("/employees/new")
	public String createEmployeeForm(Model theModel) {

		// create employee object to hold employee form data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);
		return "create_employee-form";
	}

	@PostMapping("/employees/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		employeeService.saveEmployee(theEmployee);
		System.out.println("redirecting to list employees");
		return "redirect:/employees";
	}

	@GetMapping("/employees/edit/{id}")
	public String editEmployeeform(@PathVariable Long id, Model theModel) {
		theModel.addAttribute("employee", employeeService.getEmployeeById(id));
		return "update_employee-form";
	}

	@PostMapping("/employees/{id}")
	public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee theEmployee,
			Model theModel) {
		// get employee from database by id
		Employee existingEmployee = employeeService.getEmployeeById(id);
		// existingEmployee.setId(id);
		existingEmployee.setFirstName(theEmployee.getFirstName());
		existingEmployee.setLastName(theEmployee.getLastName());
		existingEmployee.setEmail(theEmployee.getEmail());

		// save updated employee object
		employeeService.updateEmployee(existingEmployee);
		return "redirect:/employees";
	}

	// handler method to handle delete request
	@GetMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/employees";

	}

}
