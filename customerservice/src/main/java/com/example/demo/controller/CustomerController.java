package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CustomerserviceApplication;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.model.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerserviceApplication customerserviceApplication;
	CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository, CustomerserviceApplication customerserviceApplication) {
		
		this.customerRepository = customerRepository;
		this.customerserviceApplication = customerserviceApplication;
	}
	
	@PostMapping
	public Customer save(@RequestBody Customer customer)
	{
		return customerRepository.save(customer);
		
	}
	
	@GetMapping
	public List<Customer> findAll()
	{
		return customerRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Customer findById(@PathVariable("id") int id)
	{
		return customerRepository.findById(id).orElse(null);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable("id") int id,@RequestBody Customer customer)
	{
		Customer cust=customerRepository.findById(id).orElse(null);
		if(cust!=null)
		{
			cust.setName(customer.getName());
			cust.setEmail(customer.getEmail());
			cust.setMobile(customer.getMobile());
			
		Customer upcust=customerRepository.save(cust);
		//return (ResponseEntity<Customer>) ResponseEntity.ok();
			return ResponseEntity.ok().body(upcust);		
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public int delete(@PathVariable("id") int id)
	{
		if(findById(id)==null)
		{
			return 0;
		}
		else
		{
		customerRepository.deleteAll();
		return 1;
	    }
	}
}
