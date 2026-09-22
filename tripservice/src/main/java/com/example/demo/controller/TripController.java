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

import com.example.demo.client.DriverClient;
import com.example.demo.client.VehicleClient;
import com.example.demo.dao.TripRepository;
import com.example.demo.model.Trip;

@RestController
@RequestMapping("/trips")
public class TripController {
private final TripRepository tripRepository;
private final DriverClient driverClient;
private final VehicleClient vehicleClient;
public TripController(TripRepository tripRepository, DriverClient driverClient, VehicleClient vehicleClient) {
	super();
	this.tripRepository = tripRepository;
	this.driverClient = driverClient;
	this.vehicleClient = vehicleClient;
}

@PostMapping
public ResponseEntity<Trip> save(@RequestParam("driverid")int driverid,@RequestParam("vehicleid")int vehicleid,@RequestBody Trip trip)
{
	if(driverClient.findById(driverid)==null)
	{
		return ResponseEntity.notFound().build();
	}
	if(vehicleClient.findById(vehicleid)==null)
	{
		return ResponseEntity.notFound().build();
	}
	Trip trip1=trip;
	trip1.setDriverid(driverid);
	trip1.setVehicleid(vehicleid);
	trip1.setDuedate(LocalDate.now());
	Trip trip2=tripRepository.save(trip1);
	return ResponseEntity.status(HttpStatus.CREATED).body(trip2);
}

@GetMapping("/{id}")
public Trip findById(@PathVariable("id")int id)
{
	return tripRepository.findById(id).orElse(null);
}

@GetMapping
public List<Trip> findAll()
{
	return tripRepository.findAll();
}

@PutMapping("/{id}")
public ResponseEntity<Trip> update(@PathVariable("id")int id,@RequestBody Trip trip)
{
	Trip trip1=findById(id);
	if(trip1!=null)
	{
		trip1.setTitle(trip.getTitle());
		trip1.setRouteDetails(trip.getRouteDetails());
		trip1.setDuedate(trip.getDuedate());
		trip1.setDuedate(LocalDate.now());
		if(trip.getDuedate()!=null)
		{
			trip1.setDuedate(trip.getDuedate());
		}
		Trip trip2=tripRepository.save(trip1);
		return ResponseEntity.ok(trip2);
	}
	else
	{
		return ResponseEntity.notFound().build();
	}
}

@DeleteMapping("/{id}")
public ResponseEntity<Trip> delete(@PathVariable("id")int id)
{
	if(findById(id)==null)
	{
		return ResponseEntity.notFound().build();
	}
	else
	{
	tripRepository.deleteById(id);
	return ResponseEntity.ok(null);
	}
}
}