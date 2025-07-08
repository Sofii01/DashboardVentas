package com.app.dashboardventas.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record ProductRequestDto(
        @NotNull(message = "name must not be null")
        @Size(min = 1, max = 50)
        String name,
        @NotNull(message = "description must be at least 50 characters ")
        @Size(min = 50, max = 250)
        String description,
        @NotEmpty (message = "price must not empty")
        Double price
) {
}
