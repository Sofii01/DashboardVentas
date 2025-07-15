package com.app.dashboardventas.controllers;

import com.app.dashboardventas.dtos.SaleRequestDto;
import com.app.dashboardventas.dtos.SaleResponseDto;
import com.app.dashboardventas.models.entities.Sale;
import com.app.dashboardventas.services.interfaces.ISaleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    private final ISaleService saleService;

    public SaleController(ISaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva venta, con sus detalles")
    public ResponseEntity<?> createSale(@Valid @RequestBody SaleRequestDto saleRequestDto) {
        SaleResponseDto saleResponseDto = saleService.createSale(saleRequestDto);
        return new ResponseEntity<>(saleResponseDto, HttpStatus.CREATED);
    }
    @GetMapping
    @Operation(summary = "Obtener una lista de todos las ventas creadas")
    public ResponseEntity<?> getAllSales() {
        List<SaleResponseDto> saleResponseDtos = saleService.getAllSales();
        return new ResponseEntity<>(saleResponseDtos, HttpStatus.OK);
    }
}
