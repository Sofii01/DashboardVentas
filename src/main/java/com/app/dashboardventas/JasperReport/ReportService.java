package com.app.dashboardventas.JasperReport;

import com.app.dashboardventas.dtos.SaleReportDto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    public byte[] generarReporteVenta(List<SaleReportDto> datos) throws JRException {
        InputStream jasperStream = getClass().getResourceAsStream("/reports/report_sale.jasper");

        double total = datos.stream().mapToDouble(SaleReportDto::getTotalAmount).sum();


        Map<String, Object> params = new HashMap<>();
        params.put("totalAmount", total);
        params.put("reporteTitulo", "Detalle de Venta");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datos);
        JasperPrint print = JasperFillManager.fillReport(jasperStream, params, dataSource);
        return JasperExportManager.exportReportToPdf(print);
    }
}
