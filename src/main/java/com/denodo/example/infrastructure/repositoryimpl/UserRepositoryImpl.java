package com.denodo.example.infrastructure.repositoryimpl;

import com.denodo.example.infrastructure.UserRepository;
import com.denodo.example.infrastructure.entities.UserEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private static String MOST_FREQUENTLY_BUY_AGE = """
            SELECT * 
            FROM usuarios user 
            INNER JOIN compras buy ON user.user_id = buy.user_id 
            WHERE buy.fecha_compra >= ? AND buy.fecha_compra <= ?;
           """;



    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserEntity> findAgesByPurchaseDateRange(LocalDate startDate, LocalDate endDate) {
        return jdbcTemplate.query(MOST_FREQUENTLY_BUY_AGE,  new BeanPropertyRowMapper<>(UserEntity.class), startDate, endDate);
    }

}

