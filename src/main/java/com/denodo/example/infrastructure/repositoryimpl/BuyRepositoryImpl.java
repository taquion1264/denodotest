package com.denodo.example.infrastructure.repositoryimpl;

import com.denodo.example.infrastructure.BuyRepository;
import com.denodo.example.infrastructure.entities.UserPurchaseInfoEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class BuyRepositoryImpl implements BuyRepository {
    private final JdbcTemplate jdbcTemplate;


    private static final String USER_PURCHASE_INFO_QUERY_100 = """
            SELECT u.age, u.user_id, c.compra_id, c.fecha_compra, c.hora, c.importe_total 
            FROM usuarios u 
            INNER JOIN compras c ON u.user_id = c.user_id 
             WHERE c.fecha_compra >= ? AND c.fecha_compra <= ? AND c.importe_total > 100 
            ORDER BY c.importe_total DESC;
            """;


    private static final String USER_PURCHASE_INFO_QUERY = """
            SELECT u.age, u.user_id, c.compra_id, c.fecha_compra, c.hora, c.importe_total 
            FROM usuarios u 
            INNER JOIN compras c ON u.user_id = c.user_id 
             WHERE  u.user_id = ? AND c.importe_total = ?  
            ORDER BY c.importe_total DESC;
            """;


    public BuyRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserPurchaseInfoEntity> findUserInfoForHighValuePurchases(LocalDate startDate, LocalDate endDate) {
        return jdbcTemplate.query(USER_PURCHASE_INFO_QUERY_100, new BeanPropertyRowMapper<>(UserPurchaseInfoEntity.class), startDate, endDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserPurchaseInfoEntity> findUserInfoForAmountPurchases(Long userId, BigDecimal amount) {
        return jdbcTemplate.query(USER_PURCHASE_INFO_QUERY, new BeanPropertyRowMapper<>(UserPurchaseInfoEntity.class), userId, amount);
    }


}
