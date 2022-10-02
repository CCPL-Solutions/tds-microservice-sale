package co.com.viveres.susy.microservicesale.service;

import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microservicesale.client.IProductRestClient;
import co.com.viveres.susy.microservicesale.entity.SaleDetailEntity;
import co.com.viveres.susy.microservicesale.entity.SaleEntity;
import co.com.viveres.susy.microservicesale.repository.ISaleRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class SalesReportServiceImpl implements ISalesReportService {

	@Autowired
	private ISaleRepository repository;
	@Autowired
	private IProductRestClient productClientRest;

	@Override
	public ByteArrayInputStream generateSalesReport(LocalDate date) {
		List<SaleEntity> sales = this.findAllSales(date);
		return this.salesToCsv(sales);
	}

	private List<SaleEntity> findAllSales(LocalDate date) {
		return this.repository.findByDate(date);
	}

	private ByteArrayInputStream salesToCsv(List<SaleEntity> sales) {
		CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("Fecha", "Hora", "Venta", 
				"Nombre", "Marca", "Contenido", "Cantidad", "Monto");

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), csvFormat);) {

			for (SaleEntity sale : sales) {
				for (SaleDetailEntity saleDetail : sale.getSaleDetail()) {
					var product = this.findProduct(saleDetail);
					List<Object> data = this.mapRecord(saleDetail, product);
					csvPrinter.printRecord(data);
				}
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("Fail to import data to CSV file: " 
					+ e.getMessage());
		}
	}
	
	private List<Object> mapRecord(SaleDetailEntity saleDetail, 
		ProductDto product) {
		return Arrays.asList(
			String.valueOf(saleDetail.getSale().getDate()),
			String.valueOf(saleDetail.getSale().getTime()),
			String.valueOf(saleDetail.getSale().getId()),
			product.getName(),
			product.getBrand().getName(),
			String.format("%s %s", product.getContent().getValue(), product.getContent().getMeasure().getName()),
			String.valueOf(saleDetail.getNumberItems()),
			BigDecimal.valueOf(saleDetail.getPriceUnit() * saleDetail.getNumberItems()).setScale(0,BigDecimal.ROUND_DOWN)
		);
	}

	private ProductDto findProduct(SaleDetailEntity saleDetail) {
		ResponseEntity<ProductDto> responseProductService = 
				this.productClientRest.getById(saleDetail.getProductId());
		return responseProductService.getBody();	
	}	

}
