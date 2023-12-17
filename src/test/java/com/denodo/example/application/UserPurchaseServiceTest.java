package com.denodo.example.application;

import com.denodo.example.domain.Buy;
import com.denodo.example.infrastructure.BuyRepository;
import com.denodo.example.infrastructure.UserRepository;
import com.denodo.example.infrastructure.entities.UserEntity;
import com.denodo.example.infrastructure.entities.UserPurchaseInfoEntity;
import com.denodo.example.mappers.BuyMapper;
import com.denodo.example.mappers.BuyMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserPurchaseServiceTest {

    @Mock
    private BuyRepository buyRepository;

    @Mock
    private UserRepository userRepository;

    @MockBean
    private BuyMapper buyMapper;
    @InjectMocks
    private UserPurchaseService userPurchaseService;

    @Test
    void testMostFrequentAgeRangeInPurchases() {
        // Arrange
        String startDate = "2023-01-01";
        String endDate = "2023-12-31";

        List<UserEntity> userList = Arrays.asList(
                new UserEntity(1L, 25),
                new UserEntity(2L, 30),
                new UserEntity(3L, 22)
        );

        when(userRepository.findAgesByPurchaseDateRange(LocalDate.parse(startDate), LocalDate.parse(endDate)))
                .thenReturn(userList);

        // Act
        Optional<Map<String, Integer>> result = userPurchaseService.mostFrequentAgeRangeInPurchases(startDate, endDate);

        // Assert
        assertTrue(result.isPresent(), "Result should be present");
        assertTrue(result.get().containsKey("MIN_AGE"), "Result should contain MIN_AGE key");
        assertTrue(result.get().containsKey("MAX_AGE"), "Result should contain MAX_AGE key");
        assertEquals(22, result.get().get("MIN_AGE"));
        assertEquals(30, result.get().get("MAX_AGE"));
    }

    @Test
    void testMostFrequentAgeRangeInPurchasesNoData() {
        // Arrange
        String startDate = "2023-01-01";
        String endDate = "2023-12-31";

        when(userRepository.findAgesByPurchaseDateRange(LocalDate.parse(startDate), LocalDate.parse(endDate)))
                .thenReturn(Collections.emptyList());

        // Act
        Optional<Map<String, Integer>> result = userPurchaseService.mostFrequentAgeRangeInPurchases(startDate, endDate);

        // Assert
        assertTrue(result.isEmpty(), "Result should be empty");
    }


    @Test
    void testGetPurchaseDetailsNoData() {
        // Arrange
        String startDate = "2023-01-01";
        String endDate = "2023-12-31";

        when(buyRepository.findUserInfoForHighValuePurchases(LocalDate.parse(startDate), LocalDate.parse(endDate)))
                .thenReturn(Collections.emptyList());

        // Act
        Optional<List<Buy>> result = userPurchaseService.getPurchaseDetailsDateAmountFiltered(startDate, endDate);

        // Assert
        assertTrue(result.isEmpty(), "Result should be empty");
    }
}
