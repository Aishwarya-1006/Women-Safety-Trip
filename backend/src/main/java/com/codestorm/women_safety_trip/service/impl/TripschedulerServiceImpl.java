package com.codestorm.women_safety_trip.service.impl;

import com.codestorm.women_safety_trip.entity.Alert;
import com.codestorm.women_safety_trip.entity.Trip;
import com.codestorm.women_safety_trip.entity.TripStatus;
import com.codestorm.women_safety_trip.repository.AlertRepository;
import com.codestorm.women_safety_trip.repository.TripRepository;
import com.codestorm.women_safety_trip.service.TripschedulerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripschedulerServiceImpl implements TripschedulerService {

    private final TripRepository tripRepository;
    private final AlertRepository alertRepository;

    public TripschedulerServiceImpl(TripRepository tripRepository, AlertRepository alertRepository) {
        this.tripRepository = tripRepository;
        this.alertRepository = alertRepository;
    }

    @Override
    @Scheduled(fixedRate = 5000) // runs every 5s
    public void checkTripsForAutoAlert() {
        List<Trip> ongoingTrips = tripRepository.findByStatus(TripStatus.ONGOING);

        for (Trip trip : ongoingTrips) {
            if (trip.getExpectedEndTime().isBefore(LocalDateTime.now())) {
                Alert alert = new Alert();
                alert.setTripId(trip.getId()); // use tripId, not trip object
                alert.setMessage("Trip exceeded expected time. Auto-SOS triggered.");
                alert.setAlertTime(LocalDateTime.now());
                alertRepository.save(alert);
                trip.setStatus(TripStatus.ALERTED); // ✅ enum, not string
                tripRepository.save(trip);
            }
        }
    }
}
