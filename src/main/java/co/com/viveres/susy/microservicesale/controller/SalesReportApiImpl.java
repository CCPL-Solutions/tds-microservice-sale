package co.com.viveres.susy.microservicesale.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.viveres.susy.microservicesale.api.ISalesReportApi;
import co.com.viveres.susy.microservicesale.service.ISalesReportService;

@RestController
@RequestMapping("/v1/reports")
public class SalesReportApiImpl implements ISalesReportApi {

	@Autowired
	private ISalesReportService service;

	@Override
	public ResponseEntity<InputStreamResource> generateSalesReport(LocalDate date) {
		ByteArrayInputStream inputStream = this.service.generateSalesReport(date);
		String filename = "Reporte De Ventas " + date +".csv";
		InputStreamResource file = new InputStreamResource(inputStream);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

}
