package com.codestorm.women_safety_trip.service.impl;

import com.codestorm.women_safety_trip.dto.TripStartRequest;
import com.codestorm.women_safety_trip.dto.LocationUpdateRequest;
import com.codestorm.women_safety_trip.dto.SosRequest;
import com.codestorm.women_safety_trip.entity.Trip;
import com.codestorm.women_safety_trip.entity.TripStatus;
import com.codestorm.women_safety_trip.repository.TripRepository;
import com.codestorm.women_safety_trip.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public Trip startTrip(TripStartRequest request) {
        Trip trip = new Trip();
        trip.setUserId(request.getUserId());
        trip.setStartLat(request.getStartLat());
        trip.setStartLng(request.getStartLng());

        // Set current location same as start location
        trip.setCurrentLat(request.getStartLat());
        trip.setCurrentLng(request.getStartLng());

        trip.setStartTime(LocalDateTime.now());
        trip.setExpectedEndTime(request.getExpectedEndTime());
        trip.setStatus(TripStatus.ONGOING);
        return tripRepository.save(trip);
    }

    @Override
    public Trip endTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found"));

        // prevent ending twice
        if (trip.getStatus() != TripStatus.ONGOING) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trip already ended or not active");
        }

        trip.setEndTime(LocalDateTime.now());
        trip.setStatus(TripStatus.ENDED);
        return tripRepository.save(trip);
    }

    @Override
    @Transactional
    public Trip updateLocation(Long tripId, LocationUpdateRequest request) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found"));

        trip.setCurrentLat(request.getLat());
        trip.setCurrentLng(request.getLng());
        return tripRepository.save(trip);
    }

    @Override
    @Transactional
    public Trip triggerSos(Long tripId, SosRequest sosRequest) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found"));

        trip.setSosTriggered(true);
        trip.setSosTime(LocalDateTime.now());
        trip.setStatus(TripStatus.ALERTED);
        return tripRepository.save(trip);
    }
}
