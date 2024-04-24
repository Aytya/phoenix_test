package com.example.phoenix_test.controller;

import com.example.phoenix_test.entity.News;
import com.example.phoenix_test.entity.UserRequest;
import com.example.phoenix_test.service.NewsService;
import com.example.phoenix_test.service.UserRequestsService;
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

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Hello from admin only url");
    }

    private final NewsService newsService;
    private final UserRequestsService userRequestService;

    @GetMapping("/news")
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News news) {
        News createdNews = newsService.createNews(news);
        return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News newsDetails) {
        Optional<News> existingNewsOptional = newsService.getNewsById(id);
        if (existingNewsOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        News existingNews = existingNewsOptional.get();
        existingNews.setTitle(newsDetails.getTitle());
        existingNews.setContent(newsDetails.getContent());

        News updatedNews = newsService.updateNews(existingNews);
        return new ResponseEntity<>(updatedNews, HttpStatus.OK);
    }

    @GetMapping("admin_only/request")
    public ResponseEntity<List<UserRequest>> getAllUserRequests() {
        List<UserRequest> userRequests = userRequestService.getAllUserRequests();
        return new ResponseEntity<>(userRequests, HttpStatus.OK);
    }

    @PostMapping("/request/{userId}")
    public ResponseEntity<String> createUserRequest(@RequestBody UserRequest userRequest, @PathVariable Long userId) {
//        UserRequest createdUserRequest =
                userRequestService.createUserRequest(userRequest, userId);
//        return new ResponseEntity<>(createdUserRequest, HttpStatus.CREATED);
        return ResponseEntity.ok("Your request succesfully sent!");
    }
}
