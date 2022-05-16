package co.com.viveres.susy.microservicesale.dto;

import java.io.Serializable;

import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import lombok.Data;

@Data
public class SaleDetailOutputDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProductDto product;
	private QualityDto quantity;

}
