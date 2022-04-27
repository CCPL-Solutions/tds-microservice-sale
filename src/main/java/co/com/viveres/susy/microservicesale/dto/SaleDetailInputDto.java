package co.com.viveres.susy.microservicesale.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SaleDetailInputDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long productId;
	private Integer numItems;

}
