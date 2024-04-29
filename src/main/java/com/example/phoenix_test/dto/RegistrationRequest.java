package com.example.phoenix_test.dto;

import com.example.phoenix_test.entity.Role;
import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
    private String fullName;
    private String image;
    private Role role;
}
