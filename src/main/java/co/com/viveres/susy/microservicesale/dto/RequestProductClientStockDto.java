package co.com.viveres.susy.microservicesale.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RequestProductClientStockDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private Integer numberItems;

}
