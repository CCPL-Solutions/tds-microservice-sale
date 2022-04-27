package co.com.viveres.susy.microservicesale.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import co.com.viveres.susy.microservicesale.dto.validation.ICreateSaleValidation;
import lombok.Data;

@Data
public class SaleInputDto extends BaseSaleDto {

	private static final long serialVersionUID = 1L;
	@Valid
	@NotNull(groups = ICreateSaleValidation.class)
	private List<SaleDetailInputDto> details;

}
