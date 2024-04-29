package com.example.phoenix_test.mapper;

import com.example.phoenix_test.dto.UserRequestDto;
import com.example.phoenix_test.entity.UserRequest;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface UserRequestMapper extends Mappable<UserRequest, UserRequestDto> {
}
