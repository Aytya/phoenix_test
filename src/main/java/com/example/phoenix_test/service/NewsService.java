package com.example.phoenix_test.service;

import com.example.phoenix_test.dto.NewsRequestDto;
import com.example.phoenix_test.entity.News;
import com.example.phoenix_test.entity.UserRequest;
import com.example.phoenix_test.repository.NewsRepository;
import com.example.phoenix_test.repository.UserRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final UserRequestRepository userRequestRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository, UserRequestRepository userRequestRepository) {
        this.newsRepository = newsRepository;
        this.userRequestRepository = userRequestRepository;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News createNews(NewsRequestDto request) {
        News news = new News();
        news.setCreated_at(new Date());
        news.setTitle(request.getTitle());
        news.setContent(request.getContent());
        List<Long> userRequestsId = request.getUserRequestsId();
        if (userRequestsId != null && !userRequestsId.isEmpty()) {
            List<UserRequest> userRequests = this.userRequestRepository.findAllById(userRequestsId);
            news.setUserRequests(userRequests);
        }

        return newsRepository.save(news);
    }

    public News updateNews(News news) {
        return newsRepository.save(news);
    }

    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }
}

