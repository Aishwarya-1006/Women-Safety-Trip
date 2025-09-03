package com.codestorm.women_safety_trip.controller;

import com.codestorm.women_safety_trip.dto.*;
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
public class  TripController {

    private final TripService tripService;

    public TripController(TripService tripService){
        this.tripService = tripService;
    }

    // ✅ Start Trip
    @PostMapping("/start")
    public ResponseEntity<TripResponse> startTrip(@Valid @RequestBody TripStartRequest request){
        Trip saved = tripService.startTrip(request);
        return ResponseEntity.status(201).body(toResponse(saved));
    }

    // ✅ End Trip
    @PutMapping("/end/{tripId}")
    public ResponseEntity<TripResponse> endTrip(@PathVariable Long tripId) {
        Trip ended = tripService.endTrip(tripId);
        return ResponseEntity.ok(toResponse(ended));
    }

    // ✅ Update Location
    @PutMapping("/{tripId}/location")
    public ResponseEntity<TripResponse> updateLocation(@PathVariable Long tripId,
                                                       @Valid @RequestBody LocationUpdateRequest request) {
        Trip updated = tripService.updateLocation(tripId, request);
        return ResponseEntity.ok(toResponse(updated));
    }

    // ✅ Trigger SOS
    @PostMapping("/{tripId}/sos")
    public ResponseEntity<TripResponse> triggerSos(@PathVariable Long tripId,
                                                   @RequestBody SosRequest sosRequest) {
        Trip updated = tripService.triggerSos(tripId, sosRequest);
        return ResponseEntity.ok(toResponse(updated));
    }

    // 🔹 Utility method to convert Trip → TripResponse
    private TripResponse toResponse(Trip trip) {
        TripResponse res = new TripResponse();
        res.setTripId(trip.getId());
        res.setUserId(trip.getUserId());
        res.setStartTime(trip.getStartTime());
        res.setExpectedEndTime(trip.getExpectedEndTime());
        res.setStatus(trip.getStatus().name());
        res.setCurrentLat(trip.getCurrentLat());
        res.setCurrentLng(trip.getCurrentLng());
        return res;
    }
}
