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

import com.example.demo.dao.VehicleRepository;
import com.example.demo.model.Vehicle;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

	VehicleRepository vehicleRepository;

	public VehicleController(VehicleRepository vehicleRepository) {
		
		this.vehicleRepository = vehicleRepository;
	}
	
	@PostMapping
	public Vehicle save(@RequestBody Vehicle vehicle)
	{
		return vehicleRepository.save(vehicle);
	}
	
	@GetMapping
	public List<Vehicle> findAll()
	{
		return vehicleRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Vehicle findById(@PathVariable("id")int id)
	{
		return vehicleRepository.findById(id).orElse(null);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Vehicle> update(@PathVariable("id") int id, @RequestBody Vehicle vehicle)
	{
		Vehicle vehicle1=vehicleRepository.findById(id).orElse(null);
		if(vehicle1!=null)
		{
			vehicle1.setVehicleName(vehicle.getVehicleName());
			vehicle1.setModel(vehicle.getModel());
			vehicle1.setType(vehicle.getType());
			vehicle1.setDailyFee(vehicle.getDailyFee());
			
			Vehicle upvehicle= vehicleRepository.save(vehicle1);
			return ResponseEntity.ok().body(upvehicle);
			
			
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
		 vehicleRepository.deleteAll();
		 return 1;
	}
	}	
}
