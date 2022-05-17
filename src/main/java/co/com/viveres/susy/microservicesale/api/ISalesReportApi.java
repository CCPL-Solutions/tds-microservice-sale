package co.com.viveres.susy.microservicesale.api;

import java.time.LocalDate;

import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Sales Report Controller", description = "Sales reporting management")
public interface ISalesReportApi {

	@ApiOperation(
		value = "generateSalesReport", 
		notes = "This operation allows to generate sales report",
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})	
	@GetMapping(value = "")
	ResponseEntity<InputStreamResource> generateSalesReport(
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam("date") LocalDate date);

}
