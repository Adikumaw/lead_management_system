package com.jitu.lead_management.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jitu.lead_management.exception.InvalidJWTHeaderException;
import com.jitu.lead_management.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    // @Autowired
    // private CustomUserDetailsService customUserDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request,
            @SuppressWarnings("null") HttpServletResponse response,
            @SuppressWarnings("null") FilterChain filterChain)
            throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");
        String reference = null;
        String token = null;

        try {
            // extract token from request header
            token = jwtService.resolveJwtHeader(requestHeader);
            reference = this.jwtService.fetchReference(token);
        } catch (InvalidJWTHeaderException e) {
        } catch (Exception e) {
            logger.error("Reference Fetcher failed: " + e.getMessage());
        }

        if (reference != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // fetch user detail from reference
            // Removing this to prevent the dao operation cause it fails the jwt Ideology
            // UserDetails userDetails =
            // customUserDetailsService.loadUserByUsername(reference);

            // creating custom user details
            UserDetails userDetails = new User(reference, "", AuthorityUtils.NO_AUTHORITIES);

            Boolean validateToken = this.jwtService.validateToken(token, userDetails);

            if (validateToken) {
                // set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                // add Request Details to authentication
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Set the authentication
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.error("Invalid Token request: " + token);
            }
        }
        // pass further filteration to Spring Security
        filterChain.doFilter(request, response);
    }

}
