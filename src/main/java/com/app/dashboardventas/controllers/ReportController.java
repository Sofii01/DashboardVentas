package com.app.dashboardventas.controllers;

import com.app.dashboardventas.JasperReport.ReportService;
import com.app.dashboardventas.dtos.SaleReportDto;
import com.app.dashboardventas.services.interfaces.ISaleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;
    private final ISaleService saleService;

    public ReportController(ReportService reportService, ISaleService saleService) {
        this.reportService = reportService;
        this.saleService = saleService;
    }

    @GetMapping("/sales")
    @Operation(summary = "Generar reporte de ventas")
    public ResponseEntity<byte[]> generarReporteVentas() {
        try {
            List<SaleReportDto> datos = saleService.getSalesReport();

            byte[] pdf = reportService.generarReporteVentas(datos);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().filename("venta.pdf").build());

            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/sale/{id}")
    @Operation(summary = "Generar con detalles de una venta, similar a una factura ")
    public ResponseEntity<byte[]> generarCustomerInvoice(@PathVariable Long id) {
        try{
            List<SaleReportDto> sale = saleService.getSaleReportById(id);
            byte[] pdf = reportService.generarCustomerInvoice(sale);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                    ContentDisposition.attachment()
                            .filename("invoice.pdf").build());
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping ("/saleByMonth/{month}")
    @Operation(summary = "Generar reporte de ventas de un mes")
    public ResponseEntity<byte[]> generarSaleByMonth(@PathVariable Integer month) {
        try{
            List<SaleReportDto> sales = saleService.getSaleReportByMonth(month);
            byte[] pdf = reportService.generarReporteVentas(sales);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                    ContentDisposition.inline()
                            .filename("monthly_sales_report_" + month + ".pdf").build());
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
