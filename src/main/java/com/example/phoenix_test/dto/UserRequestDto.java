package com.example.phoenix_test.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserRequestDto {
    private Date createdAt;
    private String product;
    private String quantity;
    private String deliveryAddress;
    private String phoneNumber;
    private String username;
    private List<Long> newsIds;
}
