package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.BookingDto;

@FeignClient(name="book-service")
public interface BookingClient {
	@GetMapping("/books/{id}")
	public BookingDto findById(@PathVariable("id")int id);

}