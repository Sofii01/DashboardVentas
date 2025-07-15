package com.app.dashboardventas.services.impl;

import com.app.dashboardventas.dtos.ProductRequestDto;
import com.app.dashboardventas.dtos.ProductResponseDto;
import com.app.dashboardventas.mappers.IProductMapper;
import com.app.dashboardventas.models.entities.Product;
import com.app.dashboardventas.repositories.IProductRepository;
import com.app.dashboardventas.services.interfaces.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService   {
    private final IProductMapper productMapper;
    private final IProductRepository productRepository;
    public ProductServiceImpl(IProductMapper productMapper, IProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequest) {
        //find by name, don't have 2 product with the same name
        Product product = productMapper.productRequestDtoToProduct(productRequest);
        productRepository.save(product);
        return productMapper.productToProductResponseDto(product);
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto productRequest) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product with id " + productId + " not found"
                )
        );
        productRepository.delete(product);
    }

    @Override
    public ProductResponseDto getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product with id " + productId + " not found"
                )
        );
        return productMapper.productToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::productToProductResponseDto).collect(Collectors.toList());
    }
}
