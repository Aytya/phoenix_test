package com.example.phoenix_test.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class ImageDto {
    @NotNull(
            message = "Image must be not null."
    )private MultipartFile file;
}
