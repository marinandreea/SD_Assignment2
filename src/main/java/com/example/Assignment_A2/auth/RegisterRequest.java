package com.example.Assignment_A2.auth;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


    private String firstName;

    private String lastName;

    private String email;
    private String gr;
    private String username;
 //   private String password;
    private String token;
    //private Role roles;
    private String hobby;
    private String password;

}
