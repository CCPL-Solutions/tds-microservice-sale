package co.com.viveres.susy.microservicesale.dto;

import lombok.Data;

@Data
public class ResponseProductClientProductDto{

    private Long id;
    private ResponseProductClientContentDto content;
    private String name;
    private String brand;
    private Double price;

}
