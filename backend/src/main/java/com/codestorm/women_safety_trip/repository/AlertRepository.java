package com.codestorm.women_safety_trip.repository;

import com.codestorm.women_safety_trip.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    // Optional: find alerts by trip ID
    List<Alert> findByTripId(Long tripId);
}
