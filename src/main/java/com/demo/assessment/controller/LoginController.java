package com.demo.assessment.controller;

import com.demo.assessment.config.JwtUtils;
import com.demo.assessment.config.SecurityConfiguration;
import com.demo.assessment.model.LoginRequest;
import com.demo.assessment.model.LoginResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/v1")
public class LoginController {
    private final AuthenticationManager authenticationManager;

    public LoginController(SecurityConfiguration securityConfiguration) throws Exception {
        this.authenticationManager = securityConfiguration.authenticationManagerBean();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtUtils.generateToken(authentication);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new LoginResponse(jwt, request.getUsername()));
    }
}
