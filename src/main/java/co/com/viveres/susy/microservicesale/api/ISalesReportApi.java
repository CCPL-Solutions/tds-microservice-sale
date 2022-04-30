package co.com.viveres.susy.microservicesale.api;

import java.time.LocalDate;

import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ISalesReportApi {
	
	@GetMapping(value = "")
	ResponseEntity<InputStreamResource> generateSalesReport(
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam("date") LocalDate date);

}
