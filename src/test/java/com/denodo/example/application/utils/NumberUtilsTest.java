package com.denodo.example.application.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class NumberUtilsTest {

    @Test
    void testConvertToLong() {
        assertThat(NumberUtils.convertToLong("1")).isEqualTo(1L);
    }

    @Test
    void testConvertToBigDecimal() {
        assertThat(NumberUtils.convertToBigDecimal("100")).isEqualTo(new BigDecimal(100));
    }
}
