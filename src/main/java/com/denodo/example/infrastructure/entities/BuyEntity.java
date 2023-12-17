package com.denodo.example.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyEntity {

    @NotNull
    private Long userId;

    @NotNull
    private LocalDate fechaCompra;

    @NotNull
    private LocalTime hora;

    @Positive
    private BigDecimal importeTotal;
}
