package com.app.dashboardventas.JasperReport;

import com.app.dashboardventas.dtos.SaleReportDto;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import org.springframework.stereotype.Service;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.awt.print.PrinterException;
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
        System.out.println(datos);
        params.put("reporteTitulo", "Detalle de Venta");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datos);
        JasperPrint print = JasperFillManager.fillReport(jasperStream, params, dataSource);
        return JasperExportManager.exportReportToPdf(print);
    }
    public byte[] generarCustomerInvoice(List<SaleReportDto> dto) throws JRException {
        InputStream jasperStream = getClass().getResourceAsStream("/reports/customer_invoice.jasper");
        if (jasperStream == null) {
            throw new JRException("No se encontro el archivo /customer_invoice.jasper en /reports");
        }

        Map<String, Object> params = new HashMap<>();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dto);
        JasperPrint print = JasperFillManager.fillReport(jasperStream, params, dataSource);
        return JasperExportManager.exportReportToPdf(print);
    }

    //para imprimir directamente el invoice, de una venta con sus detalles
    //todavia no lo agregue al controller
    public void printInvoice(List<SaleReportDto> datos) throws JRException, PrinterException {
        InputStream jasperStream = getClass().getResourceAsStream("/reports/customer_invoice.jasper");

        Map<String, Object> params = new HashMap<>();
        params.put("totalAmount", datos.stream().mapToDouble(SaleReportDto::getTotalAmount).sum());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datos);
        JasperPrint print = JasperFillManager.fillReport(jasperStream, params, dataSource);

        // Imprimir directamente sin diálogo
        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        PrintService printService = PrintServiceLookup.lookupDefaultPrintService(); // o seleccionar una específica
        JRPrintServiceExporter exporter = new JRPrintServiceExporter();

        exporter.setExporterInput(new SimpleExporterInput(print));
        SimplePrintServiceExporterConfiguration config = new SimplePrintServiceExporterConfiguration();
        config.setPrintService(printService);
        config.setPrintRequestAttributeSet(printRequestAttributeSet);
        config.setDisplayPageDialog(false);
        config.setDisplayPrintDialog(false);

        exporter.setConfiguration(config);
        exporter.exportReport();
    }

}
