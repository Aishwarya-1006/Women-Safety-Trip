package com.codestorm.women_safety_trip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripResponse {
    private Long tripId;
    private String userId;
    private LocalDateTime startTime;
    private LocalDateTime expectedEndTime;
    private String status;
    private Double currentLat;
    private Double currentLng;
    // getters/setters
}
