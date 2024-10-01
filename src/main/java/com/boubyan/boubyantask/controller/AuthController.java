package com.boubyan.boubyantask.controller;

import com.boubyan.boubyantask.config.security.JwtTokenProvider;
import com.boubyan.boubyantask.model.LoginRequest;
import com.boubyan.boubyantask.model.TokenResponse;
import com.boubyan.boubyantask.model.dto.UserDTO;
import com.boubyan.boubyantask.service.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImp userDetailsServiceImp;
    @Value("${app.jwtExpirationInMs}")
    private Long jwtExpirationInMs;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        String token = jwtTokenProvider.generateToken(loginRequest.getUsername());
        return ResponseEntity.ok(TokenResponse.builder().accessToken(token).tokenType(jwtTokenProvider.TOKEN_PREFIX).expiresIn(jwtExpirationInMs).build());
    }

    @PostMapping("/create")
    @Valid
    @Validated
    public ResponseEntity<TokenResponse> createUser(@RequestBody @Valid @Validated UserDTO userDTO) {
        userDetailsServiceImp.createUser(userDTO);
        return ResponseEntity.ok(TokenResponse.builder().accessToken(jwtTokenProvider.generateToken(userDTO.getEmail())).tokenType(jwtTokenProvider.TOKEN_PREFIX).expiresIn(jwtExpirationInMs).build());
    }
}
