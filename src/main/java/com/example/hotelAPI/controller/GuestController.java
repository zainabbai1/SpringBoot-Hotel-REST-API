package com.example.hotelAPI.controller;

import com.example.hotelAPI.exception.ResourceNotFoundException;
import com.example.hotelAPI.model.Guest;
import com.example.hotelAPI.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GuestController 
{
    @Autowired
    GuestRepository guestRepository;

    // Get All Guests
    @GetMapping("/guests")
    public List<Guest> getAllGuests()
    {
        return guestRepository.findAll();
    }

    // Get a Single Guest
    @GetMapping("/guests/{id}")
    public Guest getGuestById(@PathVariable(value = "id") Long guestId) throws Throwable 
    {
        return (Guest) guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", guestId));
    }

    // Create a new Guest
    @PostMapping("/guests")
    public Guest createGuest(@Valid @RequestBody Guest guest)
    {
        return (Guest) guestRepository.save(guest);
    }

    // Update a Guest
    @PutMapping("/guests/{id}")
    public Guest updateGuest(@PathVariable(value = "id") Long guestId,
                           @Valid @RequestBody Guest guestDetails) throws Throwable 
    {

        Guest guest = (Guest) guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", guestId));

        guest.setName(guestDetails.getName());
        guest.setSurname(guestDetails.getSurname());
        guest.setEmail(guestDetails.getEmail());
        guest.setAddress(guestDetails.getAddress());
        guest.setCity(guestDetails.getCity());
        guest.setCountry(guestDetails.getCountry());
        guest.setPersonalId(guestDetails.getPersonalId());
        guest.setPhoneNumber(guestDetails.getPhoneNumber());

        Guest updatedGuest = (Guest) guestRepository.save(guest);
        return updatedGuest;
    }

    // Delete a Guest
    @DeleteMapping("/guests/{id}")
    public ResponseEntity<?> deleteGuest(@PathVariable(value = "id") Long guestId) throws Throwable 
    {
        Guest guest = (Guest) guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", guestId));

        guestRepository.delete(guest);

        return ResponseEntity.ok().build();
    }
}