package com.spring.usersecurity.Services.Implem;
import com.spring.usersecurity.DAO.Request.SignInRequest;
import com.spring.usersecurity.DAO.Request.SignUpRequest;
import com.spring.usersecurity.DAO.Response.JwtAuthenticationResponse;
import com.spring.usersecurity.User.User;
import com.spring.usersecurity.User.Role;
import com.spring.usersecurity.Services.AuthenticationService;
import com.spring.usersecurity.Services.JwtService;
import com.spring.usersecurity.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().nom(request.getNom()).prenom(request.getPrenom())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole())).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt)
                .id(user.getId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .role(String.valueOf(user.getRole()))
                .build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt)
                .id(user.getId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .role(String.valueOf(user.getRole()))
                .build();
    }


}
