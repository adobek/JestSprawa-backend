package com.pwr.jestsprawa.controllers;

import com.pwr.jestsprawa.exceptions.EmailAlreadyInUseException;
import com.pwr.jestsprawa.exceptions.InvalidJwtTokenException;
import com.pwr.jestsprawa.model.LoginResponseDto;
import com.pwr.jestsprawa.model.UserLoginDto;
import com.pwr.jestsprawa.model.UserRegisterDto;
import com.pwr.jestsprawa.model.User;
import com.pwr.jestsprawa.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponseDto> authenticateUser(@RequestBody UserLoginDto userLoginDto) {
        LoginResponseDto loginResponseDto = authenticationService.authenticate(userLoginDto);
        return ResponseEntity.ok(loginResponseDto);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<Object> handleEmailAlreadyInUseException(EmailAlreadyInUseException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidJwtTokenException.class)
    public ResponseEntity<Object> handleInvalidJwtTokenException(InvalidJwtTokenException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
