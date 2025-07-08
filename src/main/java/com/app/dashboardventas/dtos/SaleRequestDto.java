package com.app.dashboardventas.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record SaleRequestDto(
    List<SaleItemRequestDto> items
) {
}
