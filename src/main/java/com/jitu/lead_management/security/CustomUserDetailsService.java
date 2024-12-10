package com.jitu.lead_management.security;

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.entity.User;
import com.jitu.lead_management.service.UserService;
import com.jitu.lead_management.service.VerificationService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private VerificationService verificationService;
    // @Autowired
    // private RolesRepository rolesRepository;

    @Override
    public UserDetails loadUserByUsername(String reference) throws UsernameNotFoundException {
        // Fetch the user
        User user = userService.get(reference);

        // Check if user is verified
        verificationService.checkUserVerified(user);
        // Check if user is active
        user = verificationService.setUserActive(user);
        // Check if user login attempts are allowed
        verificationService.checkUserLoginAllowed(user);

        // Check if user login attempts are allowed
        // if (user.getLoginAttempts() >= MAX_LOGIN_ATTEMPTS) {

        // Fetch User Roles
        // List<Roles> roles = rolesRepository.findByUserId(user.getUserId());
        // if (roles == null) {
        // // throw new BadCredentialsException("Role not found with Reference : " +
        // reference);
        // }
        // List<String> roleNames = new ArrayList<String>();
        // for (Roles role : roles) {
        // roleNames.add(role.getRole());
        // }

        // Generate Authority List
        // List<GrantedAuthority> authorities =
        // AuthorityUtils.createAuthorityList(roleNames.toArray(new String[0]));

        return new org.springframework.security.core.userdetails.User(reference, user.getPassword(),
                user.isActive(),
                true, true, true, AuthorityUtils.NO_AUTHORITIES);
    }

}
