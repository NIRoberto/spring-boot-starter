package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.LoginRequest;
import com.codewithmosh.store.dtos.RegisterUserRequest;
import com.codewithmosh.store.dtos.UpdateUserRequest;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
     @Mapping(target = "id", source = "id")
     @Mapping(target = "name", source = "name")
     @Mapping(target = "email", source = "email")
     UserDto toDto(User user);
//
     User toEntity(RegisterUserRequest userDto);
     User UpdateUser(
             UpdateUserRequest request
     );
     LoginRequest LoginUser(
             LoginRequest request
     );

}
