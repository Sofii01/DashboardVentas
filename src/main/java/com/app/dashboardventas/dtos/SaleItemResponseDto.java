package com.app.dashboardventas.dtos;

public record SaleItemResponseDto(
        Long product_id,
        int quantity,
        Double unitPrice
) {
}
