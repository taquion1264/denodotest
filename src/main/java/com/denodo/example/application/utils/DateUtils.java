package com.denodo.example.application.utils;

import com.denodo.example.presentation.exception.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {


    public static LocalDate convertToDate(String date) {
        LocalDate localDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            localDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new InvalidInputException("Invalid date format please use yyy-MM-dd");
        }

        return localDate;
    }
}
