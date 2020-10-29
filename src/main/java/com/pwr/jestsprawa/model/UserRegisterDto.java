package com.pwr.jestsprawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UserRegisterDto {

    @Size(min = 2, max = 50)
    private String firstName;

    @Size(min = 2, max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 255)
    private String email;

    @Size(min = 8, max = 60)
    private String password;

}
