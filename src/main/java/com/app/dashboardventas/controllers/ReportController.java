package com.app.dashboardventas.controllers;

import com.app.dashboardventas.JasperReport.ReportService;
import com.app.dashboardventas.dtos.SaleReportDto;
import com.app.dashboardventas.services.interfaces.ISaleService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/sale")
    public ResponseEntity<byte[]> generarReporteEjemplo() {
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
}
