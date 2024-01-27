package com.spring.usersecurity.DAO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SignUpRequest {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String role ;
}
