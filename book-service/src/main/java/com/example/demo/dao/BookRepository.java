package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Booking;

public interface BookRepository extends JpaRepository<Booking, Integer> {

}
