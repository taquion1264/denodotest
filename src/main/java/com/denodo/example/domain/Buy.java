package com.denodo.example.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class Buy {
    private Long buyId;
    private User user;
    private Price price;
    private LocalDate purchaseDate;
    private LocalTime purchaseTime;
}
