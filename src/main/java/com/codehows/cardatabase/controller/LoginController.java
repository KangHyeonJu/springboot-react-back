package com.codehows.cardatabase.controller;

import com.codehows.cardatabase.auth.JWTService;
import com.codehows.cardatabase.dto.AccountCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials){
        UsernamePasswordAuthenticationToken cred = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        Authentication authentication = authenticationManager.authenticate(cred);
        String jwts = jwtService.getToken(authentication.getName());

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .build();
    }
}
