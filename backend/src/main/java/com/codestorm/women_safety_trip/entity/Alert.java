package com.codestorm.women_safety_trip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor


@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tripId;
    private String message;
    private LocalDateTime alertTime;

    // Constructors
    public Alert() {}
    public Alert(Long tripId, String message, LocalDateTime alertTime) {
        this.tripId = tripId;
        this.message = message;
        this.alertTime = alertTime;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public Long getTripId() { return tripId; }
    public void setTripId(Long tripId) { this.tripId = tripId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDateTime getAlertTime() { return alertTime; }
    public void setAlertTime(LocalDateTime alertTime) { this.alertTime = alertTime; }
}
