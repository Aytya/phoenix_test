package com.example.phoenix_test.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Image {
    private MultipartFile file;
}
