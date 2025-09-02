package com.codestorm.women_safety_trip.service;

import com.codestorm.women_safety_trip.dto.TripStartRequest;
import com.codestorm.women_safety_trip.dto.LocationUpdateRequest;
import com.codestorm.women_safety_trip.dto.SosRequest;
import com.codestorm.women_safety_trip.entity.Trip;

public interface TripService {
    Trip startTrip(TripStartRequest request);

    Trip endTrip(Long tripId);

    Trip updateLocation(Long tripId, LocationUpdateRequest request);

    Trip triggerSos(Long tripId, SosRequest sosRequest);
}
