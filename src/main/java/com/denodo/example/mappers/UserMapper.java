package com.denodo.example.mappers;

import com.denodo.example.domain.User;
import com.denodo.example.infrastructure.entities.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserEntity userToUserEntity(User user);

    @InheritInverseConfiguration

    User userEntityToUser(UserEntity userEntity);
}