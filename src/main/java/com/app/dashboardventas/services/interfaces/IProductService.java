package com.app.dashboardventas.services.interfaces;

import com.app.dashboardventas.dtos.ProductRequestDto;
import com.app.dashboardventas.dtos.ProductResponseDto;

import java.util.List;

public interface IProductService {
    ProductResponseDto createProduct(ProductRequestDto productRequest);
    ProductResponseDto updateProduct(ProductRequestDto productRequest);
    void deleteProduct(Long productId);
    ProductResponseDto getProduct(Long productId);
    List<ProductResponseDto> getAllProducts();

}
