package com.jitu.lead_management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jitu.lead_management.exception.LeadManagementException;
import com.jitu.lead_management.exception.UnknownErrorException;
import com.jitu.lead_management.model.JwtResponse;
import com.jitu.lead_management.model.PasswordUpdateModel;
import com.jitu.lead_management.model.ResetPasswordConfirmModel;
import com.jitu.lead_management.model.ResetPasswordRequestModel;
import com.jitu.lead_management.model.SignInModel;
import com.jitu.lead_management.model.SignInResponse;
import com.jitu.lead_management.model.SignUpModel;
import com.jitu.lead_management.service.AuthService;
import com.jitu.lead_management.service.JWTService;
import com.jitu.lead_management.service.VerificationTokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private AuthService authService;
    @Autowired
    JWTService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInModel signInRequest) {
        try {
            SignInResponse response = authService.authenticateAndGenerateTokens(signInRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @PostMapping("refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(@RequestHeader("Authorization") String refreshTokenHeader) {
        try {
            String refreshToken = jwtService.resolveJwtHeader(refreshTokenHeader);
            JwtResponse response = authService.authenticateAndRefreshToken(refreshToken);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody SignUpModel signUpModel) {
        try {
            authService.register(signUpModel);
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @PostMapping("/signup/resend-token")
    public ResponseEntity<String> resendToken(@RequestBody String reference) {
        try {
            verificationTokenService.sender(reference);

            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @PostMapping("/verify-user")
    public ResponseEntity<String> verify(@RequestParam String token) {
        try {
            authService.verify(token);

            return ResponseEntity.ok("Email verified successfully!");
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @PostMapping("/reset-password/request")
    public ResponseEntity<String> resetPasswordRequest(@RequestBody ResetPasswordRequestModel resetPasswordRequest) {
        try {
            authService.resetPasswordRequest(resetPasswordRequest);

            return ResponseEntity.status(HttpStatus.OK).body("If the email exists, a reset link has been sent.");
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @PostMapping("/reset-password/confirm")
    public ResponseEntity<String> resetPasswordConfirm(@RequestBody ResetPasswordConfirmModel resetPasswordConfirm,
            @RequestParam String token) {
        try {
            authService.resetPasswordConfirm(resetPasswordConfirm, token);

            return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully");
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @PostMapping("/update-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordUpdateModel passwordUpdateModel,
            @RequestHeader("Authorization") String jwtHeader) {
        try {
            String reference = jwtService.resolveReference(jwtHeader);
            authService.updatePassword(reference, passwordUpdateModel);

            return ResponseEntity.status(HttpStatus.OK).body("A verifation link has been sent to your email.");
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @GetMapping("/verify-password-update")
    public ResponseEntity<String> verifyPasswordUpdate(@RequestParam String token) {
        try {
            authService.verifyPasswordUpdate(token);
            return ResponseEntity.ok("Password updated successfully!");
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String jwtHeader) {
        try {
            String reference = jwtService.resolveReference(jwtHeader);

            authService.logout(reference);
            return ResponseEntity.ok("User logged out successfully!");
        } catch (LeadManagementException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @GetMapping("/test")
    public String getMethodName(@RequestParam String param) {
        return new String("sucessful: " + param);
    }

}
