package com.codestorm.women_safety_trip.controller;

import com.codestorm.women_safety_trip.entity.Alert;
import com.codestorm.women_safety_trip.repository.AlertRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
@CrossOrigin(origins = "http://localhost:3000") // allow React dev server
public class AlertController {

    private final AlertRepository alertRepository;

    public AlertController(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    // Get all alerts for a specific trip
    @GetMapping("/{tripId}/alerts")
    public List<Alert> getAlertsByTrip(@PathVariable Long tripId) {
        return alertRepository.findByTripId(tripId);
    }

    // Optional: get all alerts across all trips
    @GetMapping("/alerts")
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
}
