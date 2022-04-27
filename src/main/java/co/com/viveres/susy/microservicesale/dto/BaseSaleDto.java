package co.com.viveres.susy.microservicesale.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import co.com.viveres.susy.microservicesale.dto.validation.ICreateSaleValidation;
import lombok.Data;

@Data
public class BaseSaleDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(groups = ICreateSaleValidation.class)
	private String userName;

	@NotNull(groups = ICreateSaleValidation.class)
	private Double amount;

}
