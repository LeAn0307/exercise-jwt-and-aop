package com.example.exercise.controller;

import com.example.exercise.dto.LoginRequest;
import com.example.exercise.dto.LoginRespone;
import com.example.exercise.security.CustomUserDetails;
import com.example.exercise.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DemoRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    /*@Autowired
    private PasswordEncoder passwordEncoder;*/

    @PostMapping("/login")
    public LoginRespone authenticateUser(@RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        System.out.println(authentication);

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginRespone(jwt);
    }

    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public String randomStuff() {
        return "JWT Hợp lệ mới có thể thấy được message này";
    }
}
