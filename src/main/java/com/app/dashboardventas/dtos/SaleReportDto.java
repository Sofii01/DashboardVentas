package com.app.dashboardventas.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class SaleReportDto {
    private String productName;
    private int quantity;
    private Double unitPrice;
    private Double subTotal;
    private Double totalAmount;
}