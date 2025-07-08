package com.app.dashboardventas.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record SaleResponseDto(
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
        LocalDateTime date,
        List<SaleItemResponseDto> items ,
        Double totalAmount
        ) {

}
