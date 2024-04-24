package com.example.phoenix_test.service;

import com.example.phoenix_test.entity.News;
import com.example.phoenix_test.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News createNews(News request) {
        News news = new News();
        news.setCreated_at(new Date());
        news.setTitle(request.getTitle());
        news.setContent(request.getContent());

        return newsRepository.save(news);
    }

    public News updateNews(News news) {
        return newsRepository.save(news);
    }

    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }
}

