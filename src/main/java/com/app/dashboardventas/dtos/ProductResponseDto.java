package com.app.dashboardventas.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ProductResponseDto(
        String name,
        String description,
        Double price,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate createdAt
) {
}
