package com.denodo.example.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private Long userId;
    @NotNull
    @Min(value = 18)
    private Integer age;


}
