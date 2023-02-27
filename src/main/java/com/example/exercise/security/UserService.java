package com.example.exercise.security;

import com.example.exercise.entity.User;
import com.example.exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserById(Long userId){
        // Kiểm tra xem user có tồn tại trong database không?
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() == false) {
            throw new UsernameNotFoundException("Long " + userId);
        }
        return new CustomUserDetails(user.get());
    }
}