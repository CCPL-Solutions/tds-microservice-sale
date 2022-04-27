package co.com.viveres.susy.microservicesale.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ContentDto implements Serializable{

    private String measure;
    private Integer value;

}
