package com.denodo.example.application.utils;

import com.denodo.example.presentation.exception.InvalidInputException;

import java.math.BigDecimal;

public class NumberUtils {

    public static Long convertToLong(String id) {
        Long longId = null;
        try {
         longId= Long.parseLong(id);
        } catch (NumberFormatException | NullPointerException e) {
            throw new InvalidInputException("Invalid id format please use number");
        }

        return longId;
    }

    public static BigDecimal convertToBigDecimal(String amount) {
        BigDecimal bigDecimalAmount=null;
        try {
            bigDecimalAmount=  new BigDecimal(amount);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid amount value please use number");
        }

        return bigDecimalAmount;
    }
}

