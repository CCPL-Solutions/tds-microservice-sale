package co.com.viveres.susy.microservicesale.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.viveres.susy.microservicecommons.dto.ContentDto;
import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microservicecommons.entity.MessageEntity;
import co.com.viveres.susy.microservicecommons.exception.GenericException;
import co.com.viveres.susy.microservicecommons.repository.IMessageRepository;
import co.com.viveres.susy.microservicesale.client.IProductRestClient;
import co.com.viveres.susy.microservicesale.dto.QualityDto;
import co.com.viveres.susy.microservicesale.dto.RequestProductClientStockDto;
import co.com.viveres.susy.microservicesale.dto.SaleDetailOutputDto;
import co.com.viveres.susy.microservicesale.dto.SaleInputDto;
import co.com.viveres.susy.microservicesale.dto.SaleOutputDto;
import co.com.viveres.susy.microservicesale.entity.SaleDetailEntity;
import co.com.viveres.susy.microservicesale.entity.SaleEntity;
import co.com.viveres.susy.microservicesale.repository.ISaleRepository;
import co.com.viveres.susy.microservicesale.util.ResponseMessages;

@Service
public class SaleServiceImpl implements ISaleService {

	@Autowired
	private ISaleRepository saleRepository;
	@Autowired
	private IProductRestClient productClientRest;
	@Autowired
	private IMessageRepository messageRepository;

	@Override
	public void create(SaleInputDto request) {

		SaleEntity modelIn = this.mapInSaleDtoToEntity(request);
		SaleEntity modelOut = this.saleRepository.save(modelIn);
		this.updateProductStock(modelOut.getSaleDetail());
	}

	private SaleEntity mapInSaleDtoToEntity(SaleInputDto request) {

		SaleEntity model = new SaleEntity();
		model.setUserName(request.getUserName());
		model.setAmount(request.getAmount());
		model.setDate(LocalDate.now());
		model.setTime(LocalTime.now());

		request.getDetails().forEach(detail -> {

			ResponseEntity<ProductDto> response = this.productClientRest
					.getById(detail.getProductId());
			ProductDto product = response.getBody();
			
			if (product.getCurrentNumItems() < detail.getNumItems()) {
				throw this.throwGenericException(ResponseMessages.CANTIDAD_INSUFICIENTE_PRODUCTO, 
						product.getDescription());
			}

			SaleDetailEntity detailModel = new SaleDetailEntity();
			detailModel.setProductId(detail.getProductId());
			detailModel.setNumberItems(detail.getNumItems());
			detailModel.setPriceUnit(product.getPrice());
			model.addProduct(detailModel);
		});

		return model;
	}

	private void updateProductStock(List<SaleDetailEntity> saleDetail) {

		saleDetail.forEach(detailModel -> {
			RequestProductClientStockDto stockDto = new RequestProductClientStockDto();
			stockDto.setType("remove");
			stockDto.setNumberItems(detailModel.getNumberItems());
			
			this.productClientRest.stockManagementByProduct(detailModel.getProductId(), stockDto);
		});

	}

	private SaleOutputDto mapOutSaleEntityToDto(SaleEntity model) {

		SaleOutputDto dto = new SaleOutputDto();
		dto.setId(model.getId());
		dto.setUserName(model.getUserName());
		dto.setAmount(model.getAmount());
		dto.setTime(model.getTime());
		dto.setDate(model.getDate());
		return dto;
	}

	@Override
	public Page<SaleOutputDto> getAll(int page, int size, LocalDate date){
		Pageable pageable = PageRequest.of(page, size, Sort.by(
				Sort.Order.desc("date"),
				Sort.Order.desc("time")));
		
		Page<SaleEntity> saleEntityPage = null;
		
		if (date != null) {
			saleEntityPage = this.saleRepository.findByDate(date, pageable);
		}else {
			saleEntityPage = this.saleRepository.findAll(pageable);
		}
		
		return this.mapOutLIstSupplierEntityToDto(saleEntityPage);
	}

	private Page<SaleOutputDto> mapOutLIstSupplierEntityToDto(Page<SaleEntity> saleEntityPage) {
		return saleEntityPage.map(this::mapOutSaleEntityToDto);
	}

	@Override
	public SaleOutputDto getById(Long id) {

		SaleEntity saleEntity = this.saleRepository.findById(id)
				.orElseThrow(() -> this.throwGenericException(ResponseMessages.SALE_DOES_NOT_EXIST, String.valueOf(id)));

		SaleOutputDto saleDto = new SaleOutputDto();
		saleDto.setId(saleEntity.getId());
		saleDto.setUserName(saleEntity.getUserName());
		saleDto.setAmount(saleEntity.getAmount());
		saleDto.setTime(saleEntity.getTime());
		saleDto.setDate(saleEntity.getDate());
		saleDto.setDetail(new ArrayList<>());

		saleEntity.getSaleDetail().forEach(saleDetail -> {

			SaleDetailOutputDto detail = new SaleDetailOutputDto();

			ResponseEntity<ProductDto> responseProductService = 
					this.productClientRest.getById(saleDetail.getProductId());
			
			ProductDto product = responseProductService.getBody();

			ProductDto productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setName(product.getName());
			productDto.setBrand(product.getBrand());
			productDto.setPrice(saleDetail.getPriceUnit());
			productDto.setContent(new ContentDto());
			productDto.getContent().setMeasure(product.getContent().getMeasure());
			productDto.getContent().setValue(product.getContent().getValue());

			QualityDto quantity = new QualityDto();
			quantity.setUnits(saleDetail.getNumberItems());
			quantity.setAmount(saleDetail.getPriceUnit() * saleDetail.getNumberItems());

			detail.setProduct(productDto);
			detail.setQuantity(quantity);

			saleDto.getDetail().add(detail);

		});

		return saleDto;
	}

	@Override
	public void update(Long id, SaleInputDto request) {
		// No implementado aun.
	}

	@Override
	public void delete(Long id) {
		// No implementado aun.
	}
	
	private GenericException throwGenericException(String responseMessage, String value) {

		MessageEntity message = this.messageRepository
				.findById(responseMessage)
				.orElseThrow(NoSuchElementException::new);
		message.setDescripction(String.format(message.getDescripction(), value));

		return new GenericException(message);
	}

}
