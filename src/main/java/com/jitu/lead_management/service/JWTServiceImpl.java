package com.jitu.lead_management.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.exception.InvalidJWTHeaderException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl implements JWTService {
    // requirement :
    private static final long LONG_EXPIRATION_TIME_LIMIT = 30L * 24 * 60 * 60 * 1000;
    private static final long EXPIRATION_TIME_LIMIT = 15L * 60 * 1000;
    private static final long ONE_HOUR_EXPIRATION_TIME_LIMIT = 1L * 60 * 60 * 1000;

    // for fetching from environment variables
    // private String jwtSecret = System.getenv("JWT_SECRET_KEY");

    // for setting SecretKey when the service is started
    // private SecretKey key = Jwts.SIG.HS256.key().build(); // or HS384.key() or
    // HS512.key()

    private String jwtSecret = "e2133fa87db018b490a9a537154e570c08ef7c6bf986d876f9fa403c1521598398faca84d6bf3f86d3da06710d680b75b7519b171d44ab0aa896ddcf96749dafca2b6c26eff4bb77fa5f450607ead2a434659795f49a1a04406d5e0e5db53dbf0a50f7719a92ffc462814628bbbcf0cc2a4fe2f63cea4a13b397fa5a1c73f46ae687fbca033249694c7237bf3ff6ea6b445614d63df63a2da441048e6d8397f57a03a3720cedd1b920ae7da78cc8a5d7c2c25127bea425a97ce58cd02b05f092ff4e0b475fc39ecc0fcc3ea389d45d883786da34474a4d552eaeae25c1cab2e0adcc69f9df11289445b6ba974065aa53ef6e232b3040dacfb3b5923c422cf9d8";

    private final SecretKey key;

    // Constructor to initialize the signing key
    public JWTServiceImpl() {
        this.key = getSecretKey(); // Initialize it once
    }

    @Override
    public String generateToken(String reference) {
        // fetch authority/role from user details
        // SimpleGrantedAuthority role = (SimpleGrantedAuthority)
        // user.getAuthorities().toArray()[0];

        return Jwts.builder()
                // .claim("role", role.getAuthority())
                .subject(reference)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + EXPIRATION_TIME_LIMIT))
                .signWith(key)
                .compact();
    }

    @Override
    public String generateRefreshToken(String reference) {
        return Jwts.builder()
                // .claim("role", role.getAuthority())
                .subject(reference)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + LONG_EXPIRATION_TIME_LIMIT))
                .signWith(key)
                .compact();
    }

    public String generateResetRequestToken(String reference) {
        return Jwts.builder()
                .subject(reference)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + ONE_HOUR_EXPIRATION_TIME_LIMIT))
                .signWith(key)
                .compact();
    }

    public String generateUpdatePasswordToken(String reference) {
        return Jwts.builder()
                .subject(reference)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + ONE_HOUR_EXPIRATION_TIME_LIMIT))
                .signWith(key)
                .compact();
    }

    @Override
    public String fetchReference(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

    // public String fetchRole(String token) {
    // return
    // Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().get("role",
    // String.class);
    // }

    @Override
    public Date fetchExpirationDate(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getExpiration();
    }

    @Override
    public Boolean isTokenExpired(String token) {
        Date expiration = fetchExpirationDate(token);
        return expiration.before(new Date());
    }

    @Override
    public Boolean validateToken(String token, UserDetails user) {
        return user.getUsername().equals(fetchReference(token))
                && !isTokenExpired(token);
    }

    @Override
    public String resolveJwtHeader(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            throw new InvalidJWTHeaderException("Error: Invalid JWTHeader");
        }
        return header.substring(7);
    }

    @Override
    public String resolveReference(String header) {
        return fetchReference(resolveJwtHeader(header));
    }

    SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
