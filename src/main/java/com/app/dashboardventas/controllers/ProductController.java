package com.app.dashboardventas.controllers;


import com.app.dashboardventas.dtos.ProductRequestDto;
import com.app.dashboardventas.dtos.ProductResponseDto;
import com.app.dashboardventas.services.interfaces.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo producto")
    public ResponseEntity<?> addProduct(ProductRequestDto product) {
        ProductResponseDto new_product = productService.createProduct(product);
        return new ResponseEntity<>(new_product, HttpStatus.CREATED);
    }
    @GetMapping
    @Operation(summary = "Obtener una lista de todos los productos")
    public ResponseEntity<?> getAllProducts() {
        List<ProductResponseDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
