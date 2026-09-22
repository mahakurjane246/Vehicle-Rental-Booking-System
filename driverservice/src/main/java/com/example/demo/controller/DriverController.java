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

import com.example.demo.dao.DriverRepository;
import com.example.demo.model.Driver;

@RestController
@RequestMapping("/drivers")
public class DriverController {
	DriverRepository driverRepository;

	public DriverController(DriverRepository driverRepository) {
		
		this.driverRepository = driverRepository;
	}
	
@PostMapping
public Driver save(@RequestBody Driver driver)
{
	return driverRepository.save(driver);
}

@GetMapping
public List<Driver> findAll()
{
	
return driverRepository.findAll(); 
		
}

@GetMapping("/{id}")
public Driver findById(@PathVariable("id")int id )
{
	return driverRepository.findById(id).orElse(null);
}

@PutMapping("/{id}")
public ResponseEntity<Driver> update(@PathVariable("id")int id,@RequestBody Driver driver)
{
	Driver driver1=driverRepository.findById(id).orElse(null);
	if(driver1!=null)
	{
		driver1.setName(driver.getName());
		driver1.setEmail(driver.getEmail());
		driver1.setLicenseNumber(driver.getLicenseNumber());
		driver1.setSpecialization(driver.getSpecialization());
		
		Driver updriver=driverRepository.save(driver1);
		return ResponseEntity.ok().body(updriver);
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
        driverRepository.deleteById(id);
        return 1; 
     }
}
}
