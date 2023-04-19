package com.example.Assignment_A2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idUser;
    @Column(name = "tkn")
    private String token;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String gr;
    @Column
    private String hobby;
    @Column
    private String email;

    @Column(name = "usrname")
    private String username;
    @Column
    private String password;
    @Column(name = "roles")
    @Enumerated(EnumType.STRING)
    private Role roles;

    public User(String username, String password, Role student) {
        this.username=username;
        this.password=password;
        this.roles=student;
    }

    public User(String firstName, String lastName, String email, String gr, String username,String password, Role student) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email=email;
        this.gr=gr;
        this.username=username;
        this.password=password;
        this.roles=student;
    }

    public User(String username, Role student) {
        this.username=username;
        this.roles=student;
    }

    public int getIdStudent() {
        return idUser;
    }

    public void setIdStudent(int idStudent) {
        this.idUser = idStudent;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return roles;
    }

    public void setRole(Role role) {
        this.roles = role;
    }

    public String encryptedPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedP = passwordEncoder.encode(password);
        return encryptedP;
    }
}
