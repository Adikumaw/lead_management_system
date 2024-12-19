package com.jitu.lead_management.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "update_verification_token")
public class UpdateVerificationToken {
    private static final Long EXPIRATION = 60L * 60 * 1000; // time in milliseconds
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "data")
    private String data;
    @Column(name = "token")
    private String token;
    @Column(name = "expiry_date")
    private Date expiry;

    public UpdateVerificationToken(int userId, String data, String token) {
        this.userId = userId;
        this.data = data;
        this.token = token;
        this.expiry = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(Long expiryTimeInMilliseconds) {
        return new Date(new Date().getTime() + expiryTimeInMilliseconds);
    }

}
