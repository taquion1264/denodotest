package com.denodo.example.mappers;

import com.denodo.example.domain.Price;
import com.denodo.example.infrastructure.entities.PriceEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PriceMapper {




    PriceEntity priceToPriceEntity(Price price);

    @InheritInverseConfiguration
    Price priceEntityToPrice(PriceEntity priceEntity);
}