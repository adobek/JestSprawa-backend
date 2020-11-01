package com.pwr.jestsprawa.controllers;

import com.pwr.jestsprawa.exceptions.EmailAlreadyInUseException;
import com.pwr.jestsprawa.exceptions.InvalidJwtTokenException;
import com.pwr.jestsprawa.exceptions.UserNotFoundException;
import com.pwr.jestsprawa.model.*;
import com.pwr.jestsprawa.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        User user = authenticationService.register(userRegisterDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody UserLoginDto userLoginDto) {
        LoginResponseDto loginResponseDto = authenticationService.login(userLoginDto);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtToken> authenticateUser(HttpServletRequest httpServletRequest) {
        String newToken = authenticationService.authenticate(httpServletRequest.getRemoteUser());
        return ResponseEntity.ok(new JwtToken(newToken));
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<Object> handleEmailAlreadyInUseException(EmailAlreadyInUseException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidJwtTokenException.class)
    public ResponseEntity<Object> handleInvalidJwtTokenException(InvalidJwtTokenException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
