package co.com.viveres.susy.microservicesale.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import co.com.viveres.susy.microservicesale.dto.SaleInputDto;
import co.com.viveres.susy.microservicesale.dto.SaleOutputDto;

public interface ISaleService {

    public void create(SaleInputDto request);

    public Page<SaleOutputDto> getAll(int page, int size, LocalDate date);

    public SaleOutputDto getById(Long id);

    public void update(Long id, SaleInputDto request);

    public void delete(Long id);

}
