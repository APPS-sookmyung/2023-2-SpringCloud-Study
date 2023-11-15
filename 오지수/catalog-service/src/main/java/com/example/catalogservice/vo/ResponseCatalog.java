package com.example.catalogservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class ResponseCatalog {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;
    private Date createdAt;
}
