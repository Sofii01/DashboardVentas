package com.app.dashboardventas.dtos;

import com.app.dashboardventas.models.entities.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SaleItemRequestDto(
        @NotNull(message = "product id must not be null")
        Long product_id,
        @NotNull(message = "quantity must not be null")
        @Min(0)
        int quantity,
        @NotNull(message = "unit price must not be null")
        Double unitPrice
) {
}
