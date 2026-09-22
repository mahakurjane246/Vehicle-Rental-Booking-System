package com.example.demo.dto;

public class VehicleDto {
	int id;
	String vehiclename;
	String model;
	String type;
	int dailyfee;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVehiclename() {
		return vehiclename;
	}
	public void setVehiclename(String vehiclename) {
		this.vehiclename = vehiclename;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDailyfee() {
		return dailyfee;
	}
	public void setDailyfee(int dailyfee) {
		this.dailyfee = dailyfee;
	}

}