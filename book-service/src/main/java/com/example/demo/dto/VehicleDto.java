package com.example.demo.dto;

public class VehicleDto {

int id;
String vehicleName;
String model;
String type;
int dailyFee;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getVehicleName() {
	return vehicleName;
}
public void setVehicleName(String vehicleName) {
	this.vehicleName = vehicleName;
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
public int getDailyFee() {
	return dailyFee;
}
public void setDailyFee(int dailyFee) {
	this.dailyFee = dailyFee;
}

}
