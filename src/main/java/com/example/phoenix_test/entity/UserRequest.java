package com.example.phoenix_test.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "user_request")
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "product_name")
    private String product;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "address")
    private String deliveryAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "news_id", referencedColumnName = "id")
    private News news;

}
