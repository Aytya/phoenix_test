package com.example.phoenix_test.service;

import com.example.phoenix_test.entity.User;
import com.example.phoenix_test.entity.News;
import com.example.phoenix_test.entity.UserRequest;
import com.example.phoenix_test.repository.NewsRepository;
import com.example.phoenix_test.repository.UserRepository;
import com.example.phoenix_test.repository.UserRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequestsService {

    private final UserRequestRepository userRequestRepository;
    private final UserRepository userRepository;
    private final NewsRepository newsRepository;

    public List<UserRequest> getAllUserRequests() {
        return userRequestRepository.findAll();
    }

    public UserRequest createUserRequest(UserRequest request,  Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        UserRequest userRequest = new UserRequest();
        userRequest.setCreatedAt(new Date());
        userRequest.setProduct(request.getProduct());
        userRequest.setQuantity(request.getQuantity());
        userRequest.setDeliveryAddress(request.getDeliveryAddress());
        userRequest.setPhoneNumber(request.getPhoneNumber());
        userRequest.setUser(user);

        return userRequestRepository.save(userRequest);
    }
}

