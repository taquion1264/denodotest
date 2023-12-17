package com.denodo.example.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

@Data
@Builder
public class User {

    private Long userId;
    @Min(value = 18)
    @Max(value = 90)
    private Integer age;
}
