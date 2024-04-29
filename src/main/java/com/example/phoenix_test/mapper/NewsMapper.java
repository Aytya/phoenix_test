package com.example.phoenix_test.mapper;

import com.example.phoenix_test.dto.NewsRequestDto;
import com.example.phoenix_test.entity.News;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface NewsMapper extends Mappable<News, NewsRequestDto> {
}
