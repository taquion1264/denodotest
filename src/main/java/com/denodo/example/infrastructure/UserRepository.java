package com.denodo.example.infrastructure;

import com.denodo.example.domain.User;
import com.denodo.example.infrastructure.entities.UserEntity;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository {



    /**
     * @param startDate
     * @param endDate
     * @return list of Ages by puchase date range
     */

    List<UserEntity> findAgesByPurchaseDateRange(LocalDate startDate, LocalDate endDate);


}
