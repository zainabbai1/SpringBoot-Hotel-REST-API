package com.example.hotelAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotelAPI.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository <Reservation, Long> 
{}