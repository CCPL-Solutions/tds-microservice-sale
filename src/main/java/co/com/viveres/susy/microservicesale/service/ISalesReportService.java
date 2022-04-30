package co.com.viveres.susy.microservicesale.service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

public interface ISalesReportService {
	
	ByteArrayInputStream generateSalesReport(LocalDate date);

}
