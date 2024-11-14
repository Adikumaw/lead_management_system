package com.jitu.lead_management.entity;

import java.util.Date;

import com.jitu.lead_management.model.SignInModel;

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

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = 1;
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
        this.verified = 0;
    }

    public User(SignInModel signInModel) {
        this.name = signInModel.getName();
        this.email = signInModel.getEmail();
        this.password = signInModel.getPassword();
        this.active = 1;
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
        this.verified = 0;
    }

    public boolean isActive() {
        return active != 0 ? true : false;
    }
}
