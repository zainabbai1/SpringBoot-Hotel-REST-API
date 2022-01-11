package com.example.hotelAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotelAPI.model.Room;

@Repository
public interface RoomRepository extends JpaRepository <Room, Long>
{}