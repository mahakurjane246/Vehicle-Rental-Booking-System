package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Trip {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
int id;
String title;
String routeDetails;
int vehicleid;
int driverid;
LocalDate duedate;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getRouteDetails() {
	return routeDetails;
}
public void setRouteDetails(String routeDetails) {
	this.routeDetails = routeDetails;
}
public int getVehicleid() {
	return vehicleid;
}
public void setVehicleid(int vehicleid) {
	this.vehicleid = vehicleid;
}
public int getDriverid() {
	return driverid;
}
public void setDriverid(int driverid) {
	this.driverid = driverid;
}
public LocalDate getDuedate() {
	return duedate;
}
public void setDuedate(LocalDate duedate) {
	this.duedate = duedate;
}

}