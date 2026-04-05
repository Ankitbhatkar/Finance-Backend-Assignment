package com.example.finance.controller;


	import lombok.RequiredArgsConstructor;
	import org.springframework.web.bind.annotation.*;

import com.example.finance.dto.LoginRequest;
import com.example.finance.entity.User;
import com.example.finance.security.JwtUtil;
import com.example.finance.service.AuthService;

import org.springframework.security.authentication.*;

	@RestController
	@RequestMapping("/auth")
	@RequiredArgsConstructor
	public class AuthController {

	    private final AuthService service;
	    private final JwtUtil jwtUtil;
	    private final AuthenticationManager authManager;

	    @PostMapping("/register")
	    public User register(@RequestBody User user) {
	        return service.register(user);
	    }

	    @PostMapping("/login")
	    public String login(@RequestBody LoginRequest req) {

	        authManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        req.getUsername(),
	                        req.getPassword()
	                )
	        );

	        return jwtUtil.generateToken(req.getUsername());
	    }
	}