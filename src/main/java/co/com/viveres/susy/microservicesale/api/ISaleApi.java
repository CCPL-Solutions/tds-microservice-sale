package co.com.viveres.susy.microservicesale.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.com.viveres.susy.microservicesale.dto.SaleInputDto;
import co.com.viveres.susy.microservicesale.dto.SaleOutputDto;
import co.com.viveres.susy.microservicesale.dto.validation.ICreateSaleValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Sales Controller", description = "Sales management")
public interface ISaleApi {

	@ApiOperation(
		value = "createSale", 
		notes = "This operation allows create a new sale", 
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Ok"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})	
    @PostMapping(
        path = "", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleOutputDto> create(
        @Validated(ICreateSaleValidation.class) @RequestBody SaleInputDto request);

	@ApiOperation(
		value = "findAllSales", 
		notes = "This operation allows to list sales",
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK", response = SaleOutputDto[].class),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})	
    @GetMapping(
        path = "", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SaleOutputDto>> getAll();

	@ApiOperation(
		value = "findSaleById", 
		notes = "This operation allows to find a sale by identifier.",
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK", response = SaleOutputDto.class),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})		
    @GetMapping(
        path = "/{sale-id}", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleOutputDto> getById(@PathVariable("sale-id") Long id);

	@ApiOperation(
		value = "updateSale", 
		notes = "This operation allows to update a sale.")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})		
    @PutMapping(
        path = "/{sale-id}", 
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("sale-id") Long id, @RequestBody SaleInputDto request);

	@ApiOperation(
		value = "deleteSale", 
		notes = "This operation allows to delete a sale.")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})		
    @DeleteMapping(path = "/{sale-id}")
    public ResponseEntity<Void> delete(@PathVariable("sale-id") Long id);

}
