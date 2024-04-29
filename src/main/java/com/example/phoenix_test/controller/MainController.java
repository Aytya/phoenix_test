package com.example.phoenix_test.controller;

import com.example.phoenix_test.dto.NewsRequestDto;
import com.example.phoenix_test.dto.UserRequestDto;
import com.example.phoenix_test.entity.Image;
import com.example.phoenix_test.entity.News;
import com.example.phoenix_test.entity.UserRequest;
import com.example.phoenix_test.mapper.NewsMapper;
import com.example.phoenix_test.mapper.UserRequestMapper;
import com.example.phoenix_test.service.NewsService;
import com.example.phoenix_test.service.UserRequestsService;
import com.example.phoenix_test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final NewsService newsService;
    private final UserRequestsService userRequestService;
    private final UserService userService;
    private final NewsMapper newsMapper;
    private final UserRequestMapper userRequestMapper;

    @GetMapping({"/demo"})
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }

    @GetMapping({"/admin_only"})
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Hello from admin only url");
    }

    @GetMapping({"/news"})
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @PostMapping({"/only_admin/news"})
    public ResponseEntity<News> createNews(@RequestBody NewsRequestDto newsRequestDto) {
        News news = newsService.createNews(newsRequestDto);
        return new ResponseEntity(news, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News newsDetails) {
        Optional<News> existingNewsOptional = newsService.getNewsById(id);
        if (existingNewsOptional.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            News existingNews = existingNewsOptional.get();
            existingNews.setTitle(newsDetails.getTitle());
            existingNews.setContent(newsDetails.getContent());
            News updatedNews = this.newsService.updateNews(existingNews);
            return new ResponseEntity(updatedNews, HttpStatus.OK);
        }
    }

    @GetMapping({"admin_only/request"})
    public ResponseEntity<List<UserRequest>> getAllUserRequests() {
        List<UserRequest> userRequests = userRequestService.getAllUserRequests();
        return new ResponseEntity(userRequests, HttpStatus.OK);
    }

    @PostMapping({"/request/{userId}"})
    public String createUserRequest(@RequestBody UserRequestDto userRequest, @PathVariable Long userId) {
        UserRequest createdUserRequest = userRequestService.createUserRequest(userRequest, userId);
        userRequest.setUsername(createdUserRequest.getUser().getUsername());
        return "User request created succesfully";
    }

    @PostMapping(
            value = {"/{id}/image"},
            consumes = {"multipart/form-data"},
            produces = {"application/json"}
    )
    public ResponseEntity<String> uploadImage(@PathVariable Long id, Image userImage) {
        this.userService.uploadImage(id, userImage);
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
