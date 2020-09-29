package com.springsecurity.demo.controllers;

import com.springsecurity.demo.auth.AuthenticationRequest;
import com.springsecurity.demo.auth.AuthenticationResponse;
import com.springsecurity.demo.config.JWTConfig;
import com.springsecurity.demo.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/authenticate")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JWTUtil jwtTokenUtil;
    private JWTConfig jwtConfig;

    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          JWTUtil jwtTokenUtil,
                          JWTConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest req) throws Exception {
        log.info("jwt-config: " + jwtConfig.getJwtDataSource());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("incorrect username and password!!");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }
}
