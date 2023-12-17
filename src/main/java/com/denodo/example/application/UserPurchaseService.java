package com.denodo.example.application;

import com.denodo.example.application.utils.DateUtils;
import com.denodo.example.application.utils.NumberUtils;
import com.denodo.example.domain.Buy;
import com.denodo.example.infrastructure.BuyRepository;
import com.denodo.example.infrastructure.UserRepository;
import com.denodo.example.infrastructure.entities.UserEntity;
import com.denodo.example.infrastructure.entities.UserPurchaseInfoEntity;
import com.denodo.example.mappers.BuyMapper;
import com.denodo.example.mappers.UserMapper;
import com.denodo.example.presentation.exception.InvalidInputException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserPurchaseService {

    private final BuyRepository buyRepository;
    private final UserRepository userRepository;
    private final BuyMapper buyMapper;

    private static final String MIN_AGE = "MIN_AGE";
    private static final String MAX_AGE = "MAX_AGE";

    public UserPurchaseService(BuyRepository buyRepository, UserRepository userRepository, BuyMapper buyMapper, UserMapper userMapper) {
        this.buyRepository = buyRepository;
        this.userRepository = userRepository;
        this.buyMapper = buyMapper;
    }

    @Transactional(readOnly = true)
    public Optional<Map<String, Integer>> mostFrequentAgeRangeInPurchases(final String startDate, final String endDate) {
        LocalDate localStartDate = DateUtils.convertToDate(startDate);
        LocalDate localEndDate = DateUtils.convertToDate(endDate);
        validateDateParameters(localStartDate, localEndDate);

        final List<UserEntity> userList = userRepository.findAgesByPurchaseDateRange(
                localStartDate, localEndDate);


        if (userList == null || userList.isEmpty()) {
            return Optional.empty();
        }

        int minValue = Collections.min(userList, Comparator.comparingInt(UserEntity::getAge)).getAge();
        int maxValue = Collections.max(userList, Comparator.comparingInt(UserEntity::getAge)).getAge();

        Map<String, Integer> result = new HashMap<>();
        result.put(MAX_AGE, maxValue);
        result.put(MIN_AGE, minValue);

        return Optional.of(result);
    }

    @Transactional(readOnly = true)
    public Optional<List<Buy>> getPurchaseDetailsDateAmountFiltered(final String startDate, final String endDate) {
        Optional<List<Buy>> resultOptionalList = Optional.empty();
        LocalDate localStartDate = DateUtils.convertToDate(startDate);
        LocalDate localEndDate = DateUtils.convertToDate(endDate);
        validateDateParameters(localStartDate, localEndDate);
        final List<UserPurchaseInfoEntity> userPurchaseInfoEntityList = buyRepository.findUserInfoForHighValuePurchases(
                localStartDate,
                localEndDate);
        if (!CollectionUtils.isEmpty(userPurchaseInfoEntityList)) {
            resultOptionalList = Optional.of(userPurchaseInfoEntityList.stream()
                    .map(buyMapper::userPurchaseInfoToBuy)
                    .collect(Collectors.toList()));
        }
        return resultOptionalList;

    }


    @Transactional(readOnly = true)
    public Optional<List<Buy>> getPurchaseDetails(final String userId, final String price) {
        Optional<List<Buy>> resultOptionalList = Optional.empty();
        Long userIdLong = NumberUtils.convertToLong(userId);
        BigDecimal priceBigDecimal = NumberUtils.convertToBigDecimal(price);
        if (Objects.nonNull(userIdLong) && Objects.nonNull(priceBigDecimal)) {

            final List<UserPurchaseInfoEntity> userPurchaseInfoEntityList = buyRepository.findUserInfoForAmountPurchases(
                    userIdLong, priceBigDecimal);
            if (!CollectionUtils.isEmpty(userPurchaseInfoEntityList)) {
                resultOptionalList = Optional.of(userPurchaseInfoEntityList.stream()
                        .map(buyMapper::userPurchaseInfoToBuy)
                        .collect(Collectors.toList()));
            }
        }
        return resultOptionalList;
    }


    private void validateDateParameters(final LocalDate starDate, final LocalDate endDate) {
        if (starDate.isAfter(endDate)) {
            throw new InvalidInputException(
                    "Start date should be before end date");
        }
    }
}



