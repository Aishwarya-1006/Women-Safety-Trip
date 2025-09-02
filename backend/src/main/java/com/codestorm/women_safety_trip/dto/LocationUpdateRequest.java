// LocationUpdateRequest.java
package com.codestorm.women_safety_trip.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LocationUpdateRequest {
    @NotNull
    private Double lat;

    @NotNull
    private Double lng;
}
