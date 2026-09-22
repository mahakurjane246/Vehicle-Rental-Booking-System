package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.CustomerDto;

@FeignClient(name="customerservice")
public interface CustomerClient {
	@GetMapping("/customers/{id}")
	public CustomerDto findById(@PathVariable("id")int id);
	
}