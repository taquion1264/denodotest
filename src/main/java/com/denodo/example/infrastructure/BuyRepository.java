package com.denodo.example.infrastructure;

import com.denodo.example.infrastructure.entities.UserPurchaseInfoEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BuyRepository {


    /**
     * Retrieves user information with details about high-value purchases
     * made within a specified date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return A list of {@link UserPurchaseInfoEntity} objects representing
     * user details and high-value purchase information.
     */

    List<UserPurchaseInfoEntity> findUserInfoForHighValuePurchases(LocalDate startDate, LocalDate endDate);

    /**
     * Retrieves userand buy  information with details about purchases
     * made for an user with specific amount.
     *
     * @param userId The user id.
     * @param amount The amount of sale.
     * @return A list of {@link UserPurchaseInfoEntity} objects representing
     *  details purchase information.
     */

    List<UserPurchaseInfoEntity> findUserInfoForAmountPurchases(Long userId, BigDecimal amount);
}




