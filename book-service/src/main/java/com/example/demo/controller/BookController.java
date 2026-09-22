package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.clients.CustomerClients;
import com.example.demo.clients.VehicleClient;
import com.example.demo.dao.BookRepository;
import com.example.demo.model.Booking;

@RestController
@RequestMapping("/books")
public class BookController {
	BookRepository bookRepository;
	CustomerClients customerClients;
	VehicleClient vehicleClients;
	public BookController(BookRepository bookRepository, CustomerClients customerClients,
			VehicleClient vehicleClients) {
		
		this.bookRepository = bookRepository;
		this.customerClients = customerClients;
		this.vehicleClients = vehicleClients;
	}
	
	@PostMapping
	public ResponseEntity<Booking> save(@RequestParam("customerid") int customerid,@RequestParam("vehicleid")int vehicleid,@RequestBody Booking booking)
	{
		if(customerClients.findById(customerid)==null)
		{
			return ResponseEntity.notFound().build();
		}
		
		if(vehicleClients.findById(vehicleid)==null)
		{
			return ResponseEntity.notFound().build();
		}
		booking.setBookingDate(LocalDate.now());
		booking.setCustomerid(customerid);
		booking.setVehicleid(vehicleid);
		Booking book=bookRepository.save(booking);		
	    return ResponseEntity.status(HttpStatus.CREATED).body(book);
	}
	
	@GetMapping
	public List<Booking> findAll()
	{
		return bookRepository.findAll();		
	}
	
	@GetMapping("/{id}")
	public Booking findById(@PathVariable("id")int id)
	{
		return bookRepository.findById(id).orElse(null);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Booking> save(@PathVariable("id") int id,@RequestBody Booking book)
	{
		Booking book1= bookRepository.findById(id).orElse(null);
		if(book1!=null)
		{
			book1.setDurationDays(book.getDurationDays());
			book1.setStatus(book.getStatus());
			if(book.getBookingDate()!=null)
			{
			book1.setBookingDate(book.getBookingDate());
			}
			Booking upbook= bookRepository.save(book1);
			return ResponseEntity.ok().body(upbook);
		}
		
		else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public int delete(@PathVariable("id")int id)
	{
		if(findById(id)==null)
		{
			return 0;
		}
		else
		{
			bookRepository.deleteAll();
			return 1;
		}
     		
	}

}
