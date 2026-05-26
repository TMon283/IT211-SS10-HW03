package com.example.it211ss10hw03.model.dto.request;

import lombok.Data;

@Data
public class ImportRequest {
    private String sku;
    private Long quantity;
    private Long keeperId;
}