package com.example.phoenix_test.service;


import com.example.phoenix_test.entity.Image;
import com.example.phoenix_test.entity.User;
import com.example.phoenix_test.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRespository;
    private final ImageService imageService;

    public Optional<User> findByUsername(String username) {
        return userRespository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRespository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public void uploadImage(final Long id, final Image image) {
        String fileName = this.imageService.upload(image);
        userRespository.addImage(id, fileName);
    }

}
