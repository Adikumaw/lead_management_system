package com.jitu.lead_management.entity;

import java.util.Date;

import com.jitu.lead_management.model.SignUpModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private int active;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "verified")
    private int verified;

    @Column(name = "login")
    private int login;

    @Column(name = "login_attempts")
    private int loginAttempts;

    @Column(name = "refresh_token")
    private String refreshToken;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = 1;
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
        this.verified = 0;
        this.login = 0;
        this.loginAttempts = 0;
        this.refreshToken = null;
    }

    public User(SignUpModel signUpModel) {
        this.name = signUpModel.getUserName();
        this.email = signUpModel.getEmail();
        this.password = signUpModel.getPassword();
        this.active = 0;
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
        this.verified = 0;
        this.login = 0;
        this.loginAttempts = 0;
        this.refreshToken = null;
    }

    public User(int userId, SignUpModel signUpModel) {
        this.userId = userId;
        this.name = signUpModel.getUserName();
        this.email = signUpModel.getEmail();
        this.password = signUpModel.getPassword();
        this.active = 0;
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
        this.verified = 0;
        this.login = 0;
        this.loginAttempts = 0;
        this.refreshToken = null;
    }

    public boolean isActive() {
        return active != 0 ? true : false;
    }

    public boolean isLogedIn() {
        return login != 0 ? true : false;
    }

    public boolean isVerified() {
        return verified != 0 ? true : false;
    }

    public void incrementLoginAttempts() {
        loginAttempts++;
    }
}
