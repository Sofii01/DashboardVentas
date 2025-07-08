package com.app.dashboardventas.services.impl;

import com.app.dashboardventas.dtos.SaleItemRequestDto;
import com.app.dashboardventas.dtos.SaleRequestDto;
import com.app.dashboardventas.dtos.SaleResponseDto;
import com.app.dashboardventas.mappers.ISaleMapper;
import com.app.dashboardventas.models.entities.Product;
import com.app.dashboardventas.models.entities.Sale;
import com.app.dashboardventas.models.entities.SaleItem;
import com.app.dashboardventas.repositories.IProductRepository;
import com.app.dashboardventas.repositories.ISaleRepository;
import com.app.dashboardventas.services.interfaces.IProductService;
import com.app.dashboardventas.services.interfaces.ISaleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImpl implements ISaleService {
    private final ISaleRepository saleRepository;
    private final ISaleMapper saleMapper;
    private final IProductRepository productRepository;

    public SaleServiceImpl(ISaleRepository saleRepository, ISaleMapper saleMapper, IProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
        this.productRepository = productRepository;
    }

    @Override
    public SaleResponseDto createSale(SaleRequestDto saleRequestDto) {
        Sale sale =saleMapper.toEntity(saleRequestDto);
        sale.setDate(LocalDateTime.now());
        List<SaleItem> saleItems = new ArrayList<>();
        double total = 0;

        for ( SaleItemRequestDto saleItem : saleRequestDto.items() ){
            Product product = productRepository.findById(saleItem.product_id()).orElseThrow();
            SaleItem saleItemEntity = new SaleItem();
            saleItemEntity.setProduct(product);
            saleItemEntity.setQuantity(saleItem.quantity());
            saleItemEntity.setUnitPrice(saleItem.unitPrice());
            saleItemEntity.setSale(sale);
            total += saleItem.quantity() * saleItem.unitPrice();
            saleItems.add(saleItemEntity);

        }
        sale.setSaleItems(saleItems);
        sale.setTotalAmount(total);
        saleRepository.save(sale);
        return saleMapper.toDto(sale);
    }

    @Override
    public SaleResponseDto getAllSales() {
        return null;
    }

    @Override
    public void deleteSale(Long id) {

    }
}
