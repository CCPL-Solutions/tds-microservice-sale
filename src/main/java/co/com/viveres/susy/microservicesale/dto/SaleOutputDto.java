package co.com.viveres.susy.microservicesale.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class SaleOutputDto extends BaseSaleDto {

	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalTime time;
	private LocalDate date;
	private List<SaleDetailOutputDto> detail;

}
