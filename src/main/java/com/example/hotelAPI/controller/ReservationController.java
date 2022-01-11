package com.example.hotelAPI.controller;

import com.example.hotelAPI.exception.ResourceNotFoundException;
import com.example.hotelAPI.model.Reservation;
import com.example.hotelAPI.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController
{

    @Autowired
    ReservationRepository reservationRepository;

    // Get All Reservations
    @GetMapping("/reservations")
    public List<Reservation> getAllReservations()
    {
        return reservationRepository.findAll();
    }

    // Create a new Reservation
    @PostMapping("/reservations")
    public Reservation createReservation(@Valid @RequestBody Reservation reservation) 
    {
        return (Reservation) reservationRepository.save(reservation);
    }

    // Get a Single Reservation
    @GetMapping("/reservations/{id}")
    public Reservation getReservationById(@PathVariable(value = "id") Long reservationId) throws Throwable 
    {
        return (Reservation) reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));
    }

    // Update a Reservation
    @PutMapping("/reservations/{id}")
    public Reservation updateReservation(@PathVariable(value = "id") Long reservationId,
                           @Valid @RequestBody Reservation reservationDetails) throws Throwable
    {

        Reservation reservation = (Reservation) reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));

        reservation.setStartDate(reservationDetails.getStartDate());
        reservation.setEndDate(reservationDetails.getEndDate());
        reservation.setRoomId(reservationDetails.getRoomId());
        reservation.setGuestId(reservationDetails.getGuestId());
        reservation.setPrice(reservationDetails.getPrice());

        Reservation updatedReservation = (Reservation) reservationRepository.save(reservation);
        return updatedReservation;
    }

    // Delete a Reservation
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable(value = "id") Long reservationId) throws Throwable 
    {
        Reservation reservation = (Reservation) reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));

        reservationRepository.delete(reservation);

        return ResponseEntity.ok().build();
    }
}