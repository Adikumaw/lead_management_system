package com.jitu.lead_management.model;

import lombok.Data;

@Data
public class PasswordUpdateModel {
    private String oldPassword;
    private String newPassword;
}
