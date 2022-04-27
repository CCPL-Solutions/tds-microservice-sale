package co.com.viveres.susy.microservicesale.service;

import java.util.List;

import co.com.viveres.susy.microservicesale.dto.SaleInputDto;
import co.com.viveres.susy.microservicesale.dto.SaleOutputDto;

public interface ISaleService {

    public void create(SaleInputDto request);

    public List<SaleOutputDto> getAll();

    public SaleOutputDto getById(Long id);

    public void update(Long id, SaleInputDto request);

    public void delete(Long id);

}
