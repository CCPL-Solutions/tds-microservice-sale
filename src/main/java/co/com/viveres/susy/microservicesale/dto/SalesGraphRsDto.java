package co.com.viveres.susy.microservicesale.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class SalesGraphRsDto implements Serializable {

    private static final long serialVersionUID = -9068473104488540329L;

    private String graphName;
    private String label;
    private List<String> labels;
    private List<BigDecimal> amounts;
}
