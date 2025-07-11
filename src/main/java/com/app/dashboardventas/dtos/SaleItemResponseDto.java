package com.app.dashboardventas.dtos;

public record SaleItemResponseDto(
        Long product_id,
        String product_name,
        int quantity,
        Double unitPrice

) {
}
