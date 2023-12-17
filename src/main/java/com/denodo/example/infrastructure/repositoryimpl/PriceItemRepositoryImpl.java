package com.denodo.example.infrastructure.repositoryimpl;

import com.denodo.example.infrastructure.PriceItemRepository;
import com.denodo.example.domain.Price;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PriceItemRepositoryImpl implements PriceItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public PriceItemRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    private static final String INSERT_PRICE_ITEM = "INSERT INTO precios (precio) VALUES (?)";


    /**
     * {@inheritDoc}
     */
    @Override
    public void insertPriceItem(Price price) {
        jdbcTemplate.update(INSERT_PRICE_ITEM, price.getPrice());
    }
}
