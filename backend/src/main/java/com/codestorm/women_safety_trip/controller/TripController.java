package com.codestorm.women_safety_trip.controller;

import com.codestorm.women_safety_trip.dto.TripResponse;
import com.codestorm.women_safety_trip.dto.TripStartRequest;
import com.codestorm.women_safety_trip.entity.Trip;
import com.codestorm.women_safety_trip.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/trip")
@CrossOrigin(origins = "http://localhost:3000") // allow React dev server
@Validated
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService){
        this.tripService = tripService;
    }

    @PostMapping("/start")
    public ResponseEntity<TripResponse> startTrip(@Valid @RequestBody TripStartRequest request){
        Trip saved = tripService.startTrip(request);
        TripResponse res = new TripResponse();
        res.setTripId(saved.getId());
        res.setUserId(saved.getUserId());
        res.setStartTime(saved.getStartTime());
        res.setExpectedEndTime(saved.getExpectedEndTime());
        res.setStatus(saved.getStatus().name());
        res.setCurrentLat(saved.getCurrentLat());
        res.setCurrentLng(saved.getCurrentLng());
        return ResponseEntity.status(201).body(res);
    }

    @PutMapping("/end/{tripId}")
    public ResponseEntity<TripResponse> endTrip(@PathVariable Long tripId) {
        Trip ended = tripService.endTrip(tripId);
        TripResponse res = new TripResponse();
        res.setTripId(ended.getId());
        res.setUserId(ended.getUserId());
        res.setStartTime(ended.getStartTime());
        res.setExpectedEndTime(ended.getExpectedEndTime());
        res.setStatus(ended.getStatus().name());
        res.setCurrentLat(ended.getCurrentLat());
        res.setCurrentLng(ended.getCurrentLng());
        return ResponseEntity.ok(res);
    }

}
