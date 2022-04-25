package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	/*
	 * Below redundant after service layer created and repalced by service injection
	 *
	// need to inject the customer DAO
	@Autowired
	private CustomerDAO customerDAO;
	*/
	
	// need to inject the customer service
	@Autowired
	private CustomerService customerService;
		
		
	// Equivalent to RequestMapping("/list", method=RequestMethod.GET
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		List<Customer> theCustomers = customerService.getCustomers();
		
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		
		// create model atribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")	// "saveCustomer" and "customer" were set in html tag <form:form action="saveCustomer" modelAttribute="customer"
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		//save the customer
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		// get the customer from our service
		Customer theCustomer = customerService.getCustomer(theId);
		
		// set the customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// send to our form
		
		return "customer-form";
	}
	
	@GetMapping("/deleteCustomer")	// "saveCustomer" and "customer" were set in html tag <form:form action="saveCustomer" modelAttribute="customer"
	public String deleteCustomer(@RequestParam("customerId") int theId, Model theModel) {
		
		//save the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
}
