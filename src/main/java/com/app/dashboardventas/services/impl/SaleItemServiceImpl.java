package com.app.dashboardventas.services.impl;

import com.app.dashboardventas.dtos.SaleItemRequestDto;
import com.app.dashboardventas.dtos.SaleItemResponseDto;
import com.app.dashboardventas.services.interfaces.ISaleItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleItemServiceImpl implements ISaleItemService {
    @Override
    public SaleItemResponseDto createSaleItem(SaleItemRequestDto saleItemRequestDto) {
        return null;
    }

    @Override
    public List<SaleItemResponseDto> getAllSaleItems() {
        return List.of();
    }

    @Override
    public SaleItemResponseDto getSaleItemById(Long id) {
        return null;
    }
}
