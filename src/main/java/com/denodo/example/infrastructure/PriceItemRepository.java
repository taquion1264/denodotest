package com.denodo.example.infrastructure;

import com.denodo.example.domain.Price;

public interface PriceItemRepository {



    /**
     * Inserts a new price in DDBB
     *
     * @param price new price.
     */
    void insertPriceItem(Price price);

}


