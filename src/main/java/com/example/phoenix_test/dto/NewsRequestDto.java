package com.example.phoenix_test.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NewsRequestDto {
    private String title;
    private String content;
    private Date created_at;
    private List<Long> userRequestsId;
}
