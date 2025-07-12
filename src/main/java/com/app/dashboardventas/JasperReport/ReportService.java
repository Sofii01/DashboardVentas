package com.app.dashboardventas.JasperReport;

import com.app.dashboardventas.dtos.SaleReportDto;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    public byte[] generarReporteVentas(List<SaleReportDto> datos) throws JRException {
        InputStream jasperStream = getClass().getResourceAsStream("/reports/sale_report_fixed.jasper");
        if (jasperStream == null) {
            throw new JRException("No se encontró el archivo /sale_report_fixed.jasper en /reports");
       }
        double total = datos.stream().mapToDouble(SaleReportDto::getTotalAmount).sum();
        Map<String, Object> params = new HashMap<>();
        params.put("totalAmount", total);
        System.out.println(total +" totalAmount");
        params.put("reporteTitulo", "Detalle de Venta");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datos);
        JasperPrint print = JasperFillManager.fillReport(jasperStream, params, dataSource);
        return JasperExportManager.exportReportToPdf(print);
    }
    public byte[] generarCustomerInvoice(SaleReportDto dto) throws JRException {
        InputStream jasperStream = getClass().getResourceAsStream("/reports/sale_customer_invoice.jasper");
        if (jasperStream == null) {
            throw new JRException("No se encontro el archivo /sale_customer_invoice.jasper en /reports");
        }
        double total = dto.getTotalAmount();
        Map<String, Object> params = new HashMap<>();
        params.put("totalAmount", total);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(List.of(dto));
        JasperPrint print = JasperFillManager.fillReport(jasperStream, params, dataSource);
        return JasperExportManager.exportReportToPdf(print);
    }

}
