package com.codestorm.women_safety_trip.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TripStartRequest {
    @NotBlank
    private String userId;

    @NotNull
    private Double startLat;

    @NotNull
    private Double startLng;

    @NotNull
    private LocalDateTime expectedEndTime; // send ISO string from Postman

}
