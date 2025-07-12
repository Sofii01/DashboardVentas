package com.app.dashboardventas.services.interfaces;

import com.app.dashboardventas.dtos.SaleReportDto;
import com.app.dashboardventas.dtos.SaleRequestDto;
import com.app.dashboardventas.dtos.SaleResponseDto;

import java.util.List;

public interface ISaleService {
    SaleResponseDto createSale(SaleRequestDto saleRequestDto);
    List<SaleResponseDto> getAllSales();
    void deleteSale(Long id);
    List<SaleReportDto> getSalesReport();
    List<SaleReportDto> getSaleReportById(Long id);
}
