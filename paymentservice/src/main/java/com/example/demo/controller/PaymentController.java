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

import com.example.demo.client.BookingClient;
import com.example.demo.client.CustomerClient;
import com.example.demo.dao.PaymentRepository;
import com.example.demo.model.Payment;

@RestController
@RequestMapping("/payments")
public class PaymentController {
private final PaymentRepository paymentRepository;
private final BookingClient bookingClient;
private final CustomerClient customerClient;
public PaymentController(PaymentRepository paymentRepository, BookingClient bookingClient,
		CustomerClient customerClient) {
	super();
	this.paymentRepository = paymentRepository;
	this.bookingClient = bookingClient;
	this.customerClient = customerClient;
}

@PostMapping
public ResponseEntity<Payment> save(@RequestParam("bookingid")int bookingid,@RequestParam("customerid")int customerid,@RequestBody Payment payment)
{
	if(bookingClient.findById(bookingid)==null)
	{
		return ResponseEntity.notFound().build(); 
	}
	if(customerClient.findById(customerid)==null)
	{
		return ResponseEntity.notFound().build(); 
	}
	Payment pay=payment;
	pay.setBookingid(bookingid);
	pay.setCustomerid(customerid);
	pay.setPaymentDate(LocalDate.now());
	Payment pay1=paymentRepository.save(pay);
	return ResponseEntity.status(HttpStatus.CREATED).body(pay1);
}

@GetMapping("/{id}")
public Payment findById(@PathVariable("id")int id)
{
	return paymentRepository.findById(id).orElse(null);
}

@GetMapping
public List<Payment> findAll()
{
	return paymentRepository.findAll();
}

@PutMapping("/{id}")
public ResponseEntity<Payment> update(@PathVariable("id")int id,@RequestBody Payment payment)
{
	Payment payment1=findById(id);
	if(payment1!=null)
	{
		payment1.setAmount(payment.getAmount());
		payment1.setPaymentDate(LocalDate.now());
		payment1.setStatus(payment.getStatus());
		if(payment.getPaymentDate()!=null)
		{
			payment1.setPaymentDate(payment.getPaymentDate());
		}
		Payment pay=paymentRepository.save(payment1);
		return ResponseEntity.ok(pay);
	}
	else
	{
		return ResponseEntity.notFound().build();
	}
	
}

@DeleteMapping("/{id}")
public ResponseEntity<Payment> delete(@PathVariable("id")int id)
{
	if(findById(id)==null)
	{
		return ResponseEntity.notFound().build();
	}
	else
	{
	paymentRepository.existsById(id);
	return ResponseEntity.ok(null);
}
	}

}