package com.denodo.example.presentation;


import com.denodo.example.application.UserPurchaseService;
import com.denodo.example.domain.Buy;
import com.denodo.example.presentation.exception.InvalidInputException;
import com.denodo.example.presentation.exception.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final UserPurchaseService userPurchaseService;

    public PurchaseController(UserPurchaseService userPurchaseService) {
        this.userPurchaseService = userPurchaseService;
    }

    @GetMapping("/mostFrequentAgeRange")
    @ApiOperation("Get the most frequent age range in purchases within the specified date range.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the most frequent age range."),
            @ApiResponse(code = 404, message = "No data found for the specified date range.")
    })
    public ResponseEntity<Map<String, Integer>> getMostFrequentAgeRange(
            @ApiParam(value = "Start date in yyyy-MM-dd format", required = true)
            @RequestParam("startDate") String startDate,
            @ApiParam(value = "End date in yyyy-MM-dd format", required = true)
            @RequestParam("endDate") String endDate) throws ResourceNotFoundException {
        validateParameters(startDate, endDate);
        Map<String, Integer> result = userPurchaseService.mostFrequentAgeRangeInPurchases(startDate, endDate)
                .orElseThrow(() -> new ResourceNotFoundException("No data found for the specified date range."));
        return ResponseEntity.ok(result);
    }


    @GetMapping("/purchaseDetailsAmountFiltered")
    @ApiOperation("Get details of purchases within the specified date range and price bigger than 100.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the details of purchases."),
            @ApiResponse(code = 404, message = "No data found for the specified date range.")
    })
    public ResponseEntity<List<Buy>> getPurchaseDetailsAmountFiltered(
            @ApiParam(value = "Start date in yyyy-MM-dd format", required = true)
            @RequestParam("startDate") String startDate,
            @ApiParam(value = "End date in yyyy-MM-dd format", required = true)
            @RequestParam("endDate") String endDate) throws ResourceNotFoundException {
        validateParameters(startDate, endDate);
        List<Buy> result = userPurchaseService.getPurchaseDetailsDateAmountFiltered(startDate, endDate)
                .orElseThrow(() -> new ResourceNotFoundException("No data found for the specified date range."));
        return ResponseEntity.ok(result);
    }


    @GetMapping("/purchaseDetails")
    @ApiOperation("Get details of purchases for the specified user and amount of sale.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the details of purchases."),
            @ApiResponse(code = 404, message = "No data found for the specified date range.")
    })
    public ResponseEntity<List<Buy>> getPurchaseDetails(
            @ApiParam(value = "user id to find", required = true)
            @RequestParam("userId") String userId,
            @ApiParam(value = "total Amount of Sale", required = true)
            @RequestParam("amount") String amount) throws ResourceNotFoundException {
        List<Buy> result = userPurchaseService.getPurchaseDetails(userId, amount)
                .orElseThrow(() -> new ResourceNotFoundException("No data found for the specified date range."));
        return ResponseEntity.ok(result);
    }


    private void validateParameters(String firstValue, String secondValue) {
        if (StringUtils.isEmpty(firstValue) || StringUtils.isEmpty(secondValue)) {
            throw new InvalidInputException("parameters must be provided.");
        }
    }

}
