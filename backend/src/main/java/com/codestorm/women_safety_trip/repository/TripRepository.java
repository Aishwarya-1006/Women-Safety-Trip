package com.codestorm.women_safety_trip.repository;

import com.codestorm.women_safety_trip.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByStatus(String status);
    // add other queries you might need later
}
