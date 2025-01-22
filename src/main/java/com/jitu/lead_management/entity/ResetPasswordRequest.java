package com.jitu.lead_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reset_password_requests")
public class ResetPasswordRequest {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "token")
    private String token;

}
