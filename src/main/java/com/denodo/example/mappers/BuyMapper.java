package com.denodo.example.mappers;

import com.denodo.example.domain.Buy;
import com.denodo.example.infrastructure.entities.BuyEntity;
import com.denodo.example.infrastructure.entities.UserPurchaseInfoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring",uses = {UserMapper.class, PriceMapper.class})
public interface BuyMapper {


        BuyEntity buyToBuyEntity(Buy buy);

        @InheritInverseConfiguration
        Buy buyEntityToBuy(BuyEntity buyEntity);

        @Mapping(source = "userId", target = "user.userId")
        @Mapping(source = "age", target = "user.age")
        @Mapping(source = "compraId", target = "buyId")
        @Mapping(source = "fechaCompra", target = "purchaseDate")
        @Mapping(source = "hora", target = "purchaseTime")
        @Mapping(source = "importeTotal", target = "price.price")
        Buy userPurchaseInfoToBuy(UserPurchaseInfoEntity userPurchaseInfoEntity);
}