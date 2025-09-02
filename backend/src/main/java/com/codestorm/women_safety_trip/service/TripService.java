package com.codestorm.women_safety_trip.service;

import com.codestorm.women_safety_trip.dto.TripStartRequest;
import com.codestorm.women_safety_trip.entity.Trip;

public interface TripService {
    Trip startTrip(TripStartRequest request);

    Trip endTrip(Long tripId);

}
