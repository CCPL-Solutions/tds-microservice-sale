package co.com.viveres.susy.microservicesale.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.viveres.susy.microservicesale.api.ISaleApi;
import co.com.viveres.susy.microservicesale.dto.SaleInputDto;
import co.com.viveres.susy.microservicesale.dto.SaleOutputDto;
import co.com.viveres.susy.microservicesale.service.ISaleService;

@RestController
@RequestMapping("/v1/sale")
public class SaleApiImpl implements ISaleApi {

    @Autowired
    private ISaleService service;

    @Override
    public ResponseEntity<SaleOutputDto> create(SaleInputDto request) {
        service.create(request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Page<SaleOutputDto>> getAll(int page, int size, LocalDate date) {
    	Page<SaleOutputDto> response = service.getAll(page, size, date);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<SaleOutputDto> getById(Long id) {
        SaleOutputDto response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> update(Long id, SaleInputDto request) {
        service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
