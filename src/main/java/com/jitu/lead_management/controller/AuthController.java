package com.jitu.lead_management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jitu.lead_management.exception.UnknownErrorException;
import com.jitu.lead_management.exception.UserException;
import com.jitu.lead_management.model.JwtRequest;
import com.jitu.lead_management.model.JwtResponse;
import com.jitu.lead_management.model.SignUpModel;
import com.jitu.lead_management.security.CustomUserDetailsService;
import com.jitu.lead_management.service.JWTService;
import com.jitu.lead_management.service.UserAdvanceService;
import com.jitu.lead_management.service.VerificationTokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserAdvanceService userAdvanceService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private VerificationTokenService verificationTokenService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/test")
    public String getMethodName(@RequestParam String param) {
        return new String("sucessful: " + param);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signIn(@RequestBody JwtRequest request) {
        // Authenticate username and password
        this.doAuthenticate(request.getReference(), request.getPassword());
        // Fetch user details after authenticating
        UserDetails userDetails = null;
        try {
            userDetails = customUserDetailsService.loadUserByUsername(request.getReference());
        } catch (Exception e) {
            logger.error("Unknown error: ", e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
        // generate Jwt token
        String token = jwtService.generateToken(userDetails);
        // return response token
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .reference(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String reference, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(reference,
                password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Error: Invalid Username or Password !!");
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
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @PostMapping("/signin/resend-token")
    public ResponseEntity<String> resendToken(@RequestBody String reference) {
        try {
            verificationTokenService.sender(reference);

            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Unknown error: " + e.getMessage(), e);
            throw new UnknownErrorException("Error: unknown error");
        }
    }

    @GetMapping("/verify-user")
    public ResponseEntity<String> verify(@RequestParam String token) {
        boolean isVerified = userAdvanceService.verify(token);

        if (isVerified) {
            return ResponseEntity.ok("Email verified successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
        }
    }

}
