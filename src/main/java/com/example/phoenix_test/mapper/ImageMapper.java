package com.example.phoenix_test.mapper;

import com.example.phoenix_test.dto.ImageDto;
import com.example.phoenix_test.entity.Image;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface ImageMapper extends Mappable<Image, ImageDto> {
}