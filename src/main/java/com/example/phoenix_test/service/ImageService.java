package com.example.phoenix_test.service;

import com.example.phoenix_test.entity.Image;
import com.example.phoenix_test.exception.ImageUploadException;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final MinioClient minioClient;
    private String bucketName = "phoenix";

    public String upload(final Image image) {
        try {
            createBucket();
        } catch (Exception e) {
            throw new ImageUploadException("Image upload failed: " + e.getMessage());
        }
        MultipartFile file = image.getFile();
        if (file.isEmpty() || file.getOriginalFilename() == null) {
            throw new ImageUploadException("Image must have name.");
        }
        String fileName = generateFileName(file);
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e) {
            throw new ImageUploadException("Image upload failed: "
                    + e.getMessage());
        }
        saveImage(inputStream, fileName);
        return fileName;
    }

    @SneakyThrows
    private void createBucket() {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket("images")
                .build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("images").build());
        } else {
            System.out.println("Bucket 'images' already exists.");
        }
    }

    private String generateFileName(MultipartFile multipartFile) {
        String extension = getExtension(multipartFile);
        return UUID.randomUUID() + "." + extension;
    }

    private String getExtension(MultipartFile multipartFile) {
        return Objects.requireNonNull(multipartFile.getOriginalFilename())
                .substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
    }

    @SneakyThrows
    private void saveImage(InputStream inputStream, String fileName) {
        minioClient.putObject(PutObjectArgs.builder()
                .stream(inputStream,inputStream.available(),-1)
                .bucket(bucketName)
                .object(fileName)
                .build());
    }
}