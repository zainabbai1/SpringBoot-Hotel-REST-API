package com.example.hotelAPI.controller;


import com.example.hotelAPI.exception.ResourceNotFoundException;
import com.example.hotelAPI.model.Room;
import com.example.hotelAPI.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomController 
{
    @Autowired
    RoomRepository roomRepository;

    // Get All Rooms
    @GetMapping("/rooms")
    public List<Room> getAllRooms()
    {
        return roomRepository.findAll();
    }

    // Create a new Room
    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room)
    {
        return (Room) roomRepository.save(room);
    }

    // Get a Single Room
    @GetMapping("/rooms/{id}")
    public Room getRoomById(@PathVariable(value = "id") Long roomId) throws Throwable
    {
        return (Room) roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));
    }

    // Update a Room
    @PutMapping("/rooms/{id}")
    public Room updateRoom(@PathVariable(value = "id") Long roomId,
                           @Valid @RequestBody Room roomDetails) throws Throwable
    {

        Room room = (Room) roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));

        room.setRoomNumber(roomDetails.getRoomNumber());
        room.setFloor(roomDetails.getFloor());
        room.setPeople(roomDetails.getPeople());
        room.setPrice(roomDetails.getPrice());
        room.setIsAvailable(roomDetails.getIsAvailable());

        Room updatedRoom = (Room) roomRepository.save(room);
        return updatedRoom;
    }

    // Delete a Room
    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable(value = "id") Long roomId) throws Throwable 
    {
        Room room = (Room) roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));

        roomRepository.delete(room);

        return ResponseEntity.ok().build();
    }
}
