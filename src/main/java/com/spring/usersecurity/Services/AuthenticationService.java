package com.spring.usersecurity.Services;

import com.spring.usersecurity.DAO.Request.SignInRequest;
import com.spring.usersecurity.DAO.Request.SignUpRequest;
import com.spring.usersecurity.DAO.Response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
