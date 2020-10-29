package com.pwr.jestsprawa.services;

import com.pwr.jestsprawa.exceptions.EmailAlreadyInUseException;
import com.pwr.jestsprawa.model.UserRegisterDto;
import com.pwr.jestsprawa.model.Role;
import com.pwr.jestsprawa.model.RoleType;
import com.pwr.jestsprawa.model.User;
import com.pwr.jestsprawa.repositories.RoleRepository;
import com.pwr.jestsprawa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public User register(UserRegisterDto userRegisterDto) {
        Optional<User> userWithSameEmail = userRepository.findByEmailIgnoreCase(userRegisterDto.getEmail());
        if (userWithSameEmail.isPresent())
            throw new EmailAlreadyInUseException();

        Role userRole = roleRepository.findByName(RoleType.APPLICANT.getName());
        User user = new User();
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setRole(userRole);

        userRepository.save(user);
        return user;
    }

}
