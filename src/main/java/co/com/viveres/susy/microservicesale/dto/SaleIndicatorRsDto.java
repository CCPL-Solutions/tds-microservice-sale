package co.com.viveres.susy.microservicesale.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class SaleIndicatorRsDto implements Serializable {

    private static final long serialVersionUID = 6846531087950301053L;

    private BigDecimal totalAmount;

}
