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
import com.example.demo.client.TripClient;
import com.example.demo.dao.FeedbackRepository;
import com.example.demo.model.Feedback;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
private final FeedbackRepository feedbackRepository;
private final BookingClient bookingClient;
private final CustomerClient customerClient;
private final TripClient tripClient;
public FeedbackController(FeedbackRepository feedbackRepository, BookingClient bookingClient,
		CustomerClient customerClient, TripClient tripClient) {
	super();
	this.feedbackRepository = feedbackRepository;
	this.bookingClient = bookingClient;
	this.customerClient = customerClient;
	this.tripClient = tripClient;
}

@PostMapping
public ResponseEntity<Feedback> save(@RequestParam("bookingid")int bookingid,@RequestParam("customerid")int customerid,@RequestParam("tripid")int tripid,@RequestBody Feedback feedback)
{
	if(bookingClient.findById(bookingid)==null)
	{
		return ResponseEntity.notFound().build();
	}
	if(customerClient.findById(customerid)==null)
	{
		return ResponseEntity.notFound().build();
	}
	if(tripClient.findById(tripid)==null)
	{
		return ResponseEntity.notFound().build();
	}
	Feedback feed=feedback;
	feed.setBookingid(bookingid);
	feed.setCustomerid(customerid);
	feed.setTripid(tripid);
	feed.setFeedbackdate(LocalDate.now());
	Feedback feed2=feedbackRepository.save(feed);
	return ResponseEntity.status(HttpStatus.CREATED).body(feed2);
}

@GetMapping("/{id}")
public Feedback findById(@PathVariable("id")int id)
{
	return feedbackRepository.findById(id).orElse(null);
}


@GetMapping
public List<Feedback> findAll()
{
	return feedbackRepository.findAll();
}

@PutMapping("/{id}")
public ResponseEntity<Feedback> update(@PathVariable("id")int id,@RequestBody Feedback feedback)
{
	Feedback feedback1=findById(id);
	if(feedback1!=null)
	{
		feedback1.setRatingscore(feedback.getRatingscore());
		feedback1.setComments(feedback.getComments());
		if(feedback.getFeedbackdate()!=null)
		{
			feedback1.setFeedbackdate(feedback.getFeedbackdate());
		}
		Feedback feed=feedbackRepository.save(feedback1);
		return ResponseEntity.ok(feed);
	}
	else
	{
		return ResponseEntity.notFound().build();
	}
}

@DeleteMapping("/{id}")
public ResponseEntity<Feedback> delete(@PathVariable("id")int id)
{
	if(findById(id)==null)
	{
		return ResponseEntity.notFound().build();
	}
	else
	{
	feedbackRepository.deleteById(id);
	return ResponseEntity.ok(null);
    }
	}


}