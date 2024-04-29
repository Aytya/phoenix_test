package com.example.phoenix_test.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Date created_at;


    @OneToMany(mappedBy = "news", fetch = FetchType.EAGER)
    private List<UserRequest> userRequests;
}
