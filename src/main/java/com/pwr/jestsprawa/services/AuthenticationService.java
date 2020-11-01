package com.pwr.jestsprawa.services;

import com.pwr.jestsprawa.config.security.JwtTokenProvider;
import com.pwr.jestsprawa.exceptions.EmailAlreadyInUseException;
import com.pwr.jestsprawa.exceptions.UserNotFoundException;
import com.pwr.jestsprawa.model.*;
import com.pwr.jestsprawa.repositories.RoleRepository;
import com.pwr.jestsprawa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    @Transactional
    public User register(UserRegisterDto userRegisterDto) {
        Optional<User> userWithSameEmail = userRepository.findOneByEmailIgnoreCase(userRegisterDto.getEmail());
        if (userWithSameEmail.isPresent())
            throw new EmailAlreadyInUseException();

        Role userRole = roleRepository.findByName(RoleType.ROLE_APPLICANT.getName());
        User user = new User();
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setRole(userRole);

        userRepository.save(user);
        return user;
    }

    public LoginResponseDto login(UserLoginDto userLoginDto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var user = userRepository.findOneByEmailIgnoreCase(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
        var jwtToken = tokenProvider.createToken(authentication.getName(), RoleType.fromString(user.getRole().getName()).name());
        return LoginResponseDto.fromUserWithToken(user, jwtToken);
    }

    public String authenticate(String username) {
        var user = userRepository.findOneByEmailIgnoreCase(username)
                .orElseThrow(UserNotFoundException::new);
        return tokenProvider.createToken(username, RoleType.fromString(user.getRole().getName()).name());
    }

}
