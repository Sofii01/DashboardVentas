package com.app.dashboardventas.services.interfaces;

import com.app.dashboardventas.dtos.SaleItemRequestDto;
import com.app.dashboardventas.dtos.SaleItemResponseDto;

import java.util.List;

public interface ISaleItemService {
    SaleItemResponseDto createSaleItem(SaleItemRequestDto saleItemRequestDto);
    List<SaleItemResponseDto> getAllSaleItems();
    SaleItemResponseDto getSaleItemById(Long id);
}
