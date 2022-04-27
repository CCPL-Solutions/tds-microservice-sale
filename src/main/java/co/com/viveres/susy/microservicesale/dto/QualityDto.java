package co.com.viveres.susy.microservicesale.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class QualityDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer units;
	private Double amount;

}
