package com.denodo.example.presentation;

import com.denodo.example.application.UserPurchaseService;
import com.denodo.example.domain.Buy;
import com.denodo.example.domain.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPurchaseService userPurchaseService;

    @InjectMocks
    private PurchaseController purchaseController;

    @Test
    public void testGetMostFrequentAgeRange() throws Exception {
        // Mocking the service response
        Map<String, Integer> mockResult = new HashMap<>();
        mockResult.put("18-25", 5);
        when(userPurchaseService.mostFrequentAgeRangeInPurchases("2022-01-01", "2022-12-31"))
                .thenReturn(java.util.Optional.of(mockResult));

        // Performing the request and asserting the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/purchases/most_frequent_age_range")
                        .param("startDate", "2022-01-01").param("endDate", "2022-12-31"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetMostFrequentAgeRangeNoDataFound() throws Exception {
        // Mocking the service response when no data found
        when(userPurchaseService.mostFrequentAgeRangeInPurchases("2022-01-01", "2022-12-31"))
                .thenReturn(java.util.Optional.empty());

        // Performing the request and asserting the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/purchases/most_frequent_age_range")
                        .param("startDate", "2022-01-01").param("endDate", "2022-12-31"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetPurchaseDetails() throws Exception {
        // Mocking the service response
        List<Buy> mockResult = Collections.singletonList(Buy.builder()
                .buyId(1L)
                .price(Price.builder()
                        .price(BigDecimal.TEN)
                        .priceId(2L)
                        .build())
                .build());
        when(userPurchaseService.getPurchaseDetails("userId", "100.00"))
                .thenReturn(java.util.Optional.of(mockResult));

        // Performing the request and asserting the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/purchases/purchase_details/{userId}", "userId")
                        .param("amount", "100.00"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].buyId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price.price").value(10));
    }

    @Test
    public void testGetPurchaseDetailsNoDataFound() throws Exception {
        // Mocking the service response when no data found
        when(userPurchaseService.getPurchaseDetails("userId", "100.00"))
                .thenReturn(java.util.Optional.empty());

        // Performing the request and asserting the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/purchases/purchase_details/{userId}", "userId")
                        .param("amount", "100.00"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
