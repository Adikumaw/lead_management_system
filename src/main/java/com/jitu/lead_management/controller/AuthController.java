package com.jitu.lead_management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jitu.lead_management.exception.LeadManagementException;
import com.jitu.lead_management.exception.UnknownErrorException;
import com.jitu.lead_management.model.JwtResponse;
import com.jitu.lead_management.model.SignInModel;
import com.jitu.lead_management.model.SignUpModel;
import com.jitu.lead_management.service.AuthService;
import com.jitu.lead_management.service.UserAdvanceService;
import com.jitu.lead_management.service.VerificationTokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserAdvanceService userAdvanceService;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private AuthService authService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signIn(@RequestBody SignInModel signInRequest) {
        try {
            JwtResponse response = authService.authenticateAndGenerateToken(signInRequest);
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
            if (userAdvanceService.register(signUpModel)) {
                return ResponseEntity.status(HttpStatus.OK).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register");
            }
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

    @GetMapping("/verify-user")
    public ResponseEntity<String> verify(@RequestParam String token) {
        try {
            if (userAdvanceService.verify(token)) {
                return ResponseEntity.ok("Email verified successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
            }
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
