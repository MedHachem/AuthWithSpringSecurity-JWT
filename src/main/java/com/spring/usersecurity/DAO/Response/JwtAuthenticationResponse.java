package com.spring.usersecurity.DAO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String nom;

    private String prenom;
    private String token ;
    private Long  id;
    private String role;
}
