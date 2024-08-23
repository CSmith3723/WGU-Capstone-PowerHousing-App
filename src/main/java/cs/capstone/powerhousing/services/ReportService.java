package cs.capstone.powerhousing.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {


    DataSource dataSource;

    public ReportService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public byte[] generatePDF() throws JRException, IOException, SQLException {


        InputStream reportStream = new ClassPathResource("reports/saved_profiles_report.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        Map<String, Object> parameters = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }


    public String generateHTMLReport() {


            try {
                InputStream reportStream = new ClassPathResource("reports/saved_profiles_report.jrxml").getInputStream();
                JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

                Map<String, Object> parameters = new HashedMap<>();

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

                HtmlExporter htmlExporter = new HtmlExporter();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
                htmlExporter.setExporterInput(exporterInput);

                SimpleHtmlExporterOutput exporterOutput = new SimpleHtmlExporterOutput(outputStream);
                htmlExporter.setExporterOutput(exporterOutput);

                SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
                htmlExporter.setConfiguration(configuration);

                htmlExporter.exportReport();

                return outputStream.toString("UTF-8");
            } catch (Exception e) {
                throw new RuntimeException("Failed to generate report.", e);
            }
    }


}
