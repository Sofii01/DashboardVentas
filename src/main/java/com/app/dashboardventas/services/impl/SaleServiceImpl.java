package com.app.dashboardventas.services.impl;

import com.app.dashboardventas.dtos.*;
import com.app.dashboardventas.mappers.ISaleItemMapper;
import com.app.dashboardventas.mappers.ISaleMapper;
import com.app.dashboardventas.models.entities.Product;
import com.app.dashboardventas.models.entities.Sale;
import com.app.dashboardventas.models.entities.SaleItem;
import com.app.dashboardventas.repositories.IProductRepository;
import com.app.dashboardventas.repositories.ISaleRepository;
import com.app.dashboardventas.services.interfaces.ISaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements ISaleService {
    private final ISaleRepository saleRepository;
    private final ISaleMapper saleMapper;
    private final ISaleItemMapper saleItemMapper;
    private final IProductRepository productRepository;

    public SaleServiceImpl(ISaleRepository saleRepository, ISaleMapper saleMapper, ISaleItemMapper saleItemMapper, IProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
        this.saleItemMapper = saleItemMapper;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public SaleResponseDto createSale(SaleRequestDto saleRequestDto) {
        Sale sale =saleMapper.toEntity(saleRequestDto);
        sale.setDate(LocalDateTime.now());
        List<SaleItem> saleItems = new ArrayList<>();
        double total = 0;

        for ( SaleItemRequestDto saleItem : saleRequestDto.items() ){
            Product product = productRepository.findById(saleItem.product_id()).orElseThrow(
                    ()-> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Product with id " + saleItem.product_id() + " not found"
                    )
            );
            SaleItem saleItemEntity = new SaleItem();
            saleItemEntity.setProduct(product);
            saleItemEntity.setQuantity(saleItem.quantity());
            saleItemEntity.setUnitPrice(product.getPrice());
            saleItemEntity.setSale(sale);
            total += saleItem.quantity() * product.getPrice();
            saleItems.add(saleItemEntity);

        }
        sale.setSaleItems(saleItems);
        sale.setTotalAmount(total);
        saleRepository.save(sale);
        List<SaleItemResponseDto> itemsResponse = saleItems.stream().map(saleItemMapper::toDto).toList();
        SaleResponseDto saleResponseDto = saleMapper.toDto(sale);
        saleResponseDto.setItems(itemsResponse);
        return saleResponseDto;
    }

    @Override
    public List<SaleResponseDto> getAllSales() {
        List<Sale> sales = saleRepository.findAll();

        return sales.stream().map(sale -> {
            List<SaleItemResponseDto> items = sale.getSaleItems()
                    .stream()
                    .map(saleItemMapper::toDto)
                    .toList();

            SaleResponseDto dto = saleMapper.toDto(sale);
            dto.setItems(items);
            return dto;
        }).toList();
    }

    @Override
    public void deleteSale(Long id) {

    }

    @Override
    public List<SaleReportDto> getSalesReport() {
        List<SaleResponseDto> saleResponseDtos = this.getAllSales();
        return this.getListSaleReport(saleResponseDtos);

    }

    @Override
    public List<SaleReportDto> getSaleReportById(Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale with id " + id + " not found")
        );
        Double total = sale.getTotalAmount();
        List<SaleItem> saleItems = sale.getSaleItems();
        return saleItems.stream().map(i-> {
            String productName = i.getProduct().getName();
            Double subtotal = i.getQuantity() * i.getUnitPrice();
            return new SaleReportDto(productName, i.getQuantity(), i.getUnitPrice(), subtotal, total);
        }).toList();
    }
    @Override
    public List<SaleReportDto> getSaleReportByMonth(Integer month) {
        if (month < 1 || month > 12) {
            throw new RuntimeException("Invalid month");
        }
        int year = LocalDate.now().getYear();
        List<Sale> list = saleRepository.findAllByMonthAndYear(month, year);
        List<SaleResponseDto> saleResponseDtos = list.stream().map(sale -> {
            List<SaleItemResponseDto> items = sale.getSaleItems()
                    .stream()
                    .map(saleItemMapper::toDto)
                    .toList();

            SaleResponseDto dto = saleMapper.toDto(sale);
            dto.setItems(items);
            return dto;
        }).toList();;
        return this.getListSaleReport(saleResponseDtos);
    }



    public List<SaleReportDto> getListSaleReport(List<SaleResponseDto> saleResponseDtos) {
        Map<String, List<SaleItemResponseDto>> itemsByProductName = saleResponseDtos.stream()
                .flatMap(sale -> sale.getItems().stream())
                .collect(Collectors.groupingBy(SaleItemResponseDto::product_name));

        return itemsByProductName.entrySet().stream()
                .map(entry -> {
                    String productName = entry.getKey();
                    List<SaleItemResponseDto> items = entry.getValue();

                    int totalQuantity = items.stream().mapToInt(SaleItemResponseDto::quantity).sum();
                    Double unitPrice = items.get(0).unitPrice();
                    Double subtotal = totalQuantity * unitPrice;
                    Double total = subtotal;
                    return new SaleReportDto(productName, totalQuantity, unitPrice, subtotal, total);
                })
                .toList();
    }

}
