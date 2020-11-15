package com.pwr.jestsprawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class LoginResponseDto {

    private String token;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private Set<IssueDto> issues;

    public static LoginResponseDto fromUserWithToken(User user, String token) {
        return new LoginResponseDto(token,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.getIssues().stream().map(IssueDto::fromIssue).collect(Collectors.toSet()));
    }
}
