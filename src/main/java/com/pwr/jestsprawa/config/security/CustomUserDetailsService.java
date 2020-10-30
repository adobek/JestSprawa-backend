package com.pwr.jestsprawa.config.security;

import com.pwr.jestsprawa.model.Role;
import com.pwr.jestsprawa.model.RoleType;
import com.pwr.jestsprawa.repositories.RoleRepository;
import com.pwr.jestsprawa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByEmailIgnoreCase(username)
                .map(this::createUser)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username with email %s not found", username)));
    }

    private User createUser(com.pwr.jestsprawa.model.User user) {
        Optional<Role> userRole = roleRepository.findById(user.getRole().getId());
        RoleType roleType = RoleType.fromString(userRole.get().getName());
        SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority(roleType.name());
        return new User(user.getEmail(), user.getPasswordHash(), Collections.singletonList(userAuthority));
    }

}
