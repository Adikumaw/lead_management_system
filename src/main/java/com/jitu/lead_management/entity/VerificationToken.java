package com.jitu.lead_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "verification_tokens")
public class VerificationToken {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "token")
    private String token;
}
