package com.example.demo.dto;

import java.time.LocalDate;

public class BookingDto {
	int id;
	int customerid;
	int vehicleid;
	LocalDate bookingdate;
	int durationDays;
	String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(int vehicleid) {
		this.vehicleid = vehicleid;
	}
	public LocalDate getBookingdate() {
		return bookingdate;
	}
	public void setBookingdate(LocalDate bookingdate) {
		this.bookingdate = bookingdate;
	}
	public int getDurationDays() {
		return durationDays;
	}
	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}