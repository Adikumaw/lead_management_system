package com.jitu.lead_management.entity;

import java.util.Calendar;
import java.util.Date;

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
@Table(name = "verification_token")
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24; // time in minutes

    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "token")
    private String token;
    @Column(name = "expiry_date")
    private Date expiryDate;

    public VerificationToken(String token, int userId) {
        this.token = token;
        this.userId = userId;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
