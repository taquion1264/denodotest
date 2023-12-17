package com.denodo.example.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Price {

    BigDecimal price;
    private Long priceId;
}
