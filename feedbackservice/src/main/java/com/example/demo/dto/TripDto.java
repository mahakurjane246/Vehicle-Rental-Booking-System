package com.example.demo.dto;

public class TripDto {
	int id;
	String title;
	String routeDetails;
	int vehicleid;
	int driverid;
	int duedate;
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
	public int getDuedate() {
		return duedate;
	}
	public void setDuedate(int duedate) {
		this.duedate = duedate;
	}

}