package com.app.dashboardventas.services.interfaces;

import com.app.dashboardventas.dtos.SaleRequestDto;
import com.app.dashboardventas.dtos.SaleResponseDto;

public interface ISaleService {
    SaleResponseDto createSale(SaleRequestDto saleRequestDto);
    SaleResponseDto getAllSales();
    void deleteSale(Long id);
}
