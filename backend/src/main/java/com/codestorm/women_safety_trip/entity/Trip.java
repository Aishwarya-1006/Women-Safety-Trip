package com.codestorm.women_safety_trip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId; // who started the trip

    private LocalDateTime startTime;
    private LocalDateTime expectedEndTime;
    private LocalDateTime endTime; // null until trip ended

    private Double startLat;
    private Double startLng;

    private Double currentLat;
    private Double currentLng;

    @Enumerated(EnumType.STRING)
    private TripStatus status;


    // getters and setters below (omitted for brevity in this snippet)
    // generate them in IDE or add Lombok if preferred
}
