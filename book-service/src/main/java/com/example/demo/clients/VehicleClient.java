package com.example.demo.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.VehicleDto;
@FeignClient("vehicleservice")
public interface VehicleClient{
	@GetMapping("/vehicles/{id}")
	public VehicleDto findById(@PathVariable("id")int id );

}
