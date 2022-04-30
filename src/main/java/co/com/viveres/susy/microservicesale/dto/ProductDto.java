package co.com.viveres.susy.microservicesale.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import co.com.viveres.susy.microservicesale.dto.validation.ICreateSaleValidation;
import lombok.Data;

@Data
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(groups = ICreateSaleValidation.class)
	private Long id;

	private String name;
	private BrandDto brand;
	private Double price;
	private ContentDto content;
}
