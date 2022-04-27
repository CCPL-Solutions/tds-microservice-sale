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

public interface ISaleApi {

    @PostMapping(
        path = "", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleOutputDto> create(
        @Validated(ICreateSaleValidation.class) @RequestBody SaleInputDto request);

    @GetMapping(
        path = "", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SaleOutputDto>> getAll();

    @GetMapping(
        path = "/{sale-id}", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleOutputDto> getById(@PathVariable("sale-id") Long id);

    @PutMapping(
        path = "/{sale-id}", 
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("sale-id") Long id, @RequestBody SaleInputDto request);

    @DeleteMapping(path = "/{sale-id}")
    public ResponseEntity<Void> delete(@PathVariable("sale-id") Long id);

}
