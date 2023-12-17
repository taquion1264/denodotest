package com.denodo.example.application.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class DateUtilsTest {

    @Test
    void testConvertToDate() {
        assertThat(DateUtils.convertToDate("2020-01-01")).isEqualTo(LocalDate.of(2020, 1, 1));
    }
}
