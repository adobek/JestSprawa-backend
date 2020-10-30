package com.pwr.jestsprawa.config.security;


import com.pwr.jestsprawa.exceptions.InvalidJwtTokenException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final Key key;

    @Value("${security.authoritiesKey}")
    private String authoritiesKey;

    @Value("${security.authorizationHeader}")
    private String authorizationKey;

    @Value("${security.token.prefix}")
    private String tokenPrefix;

    @Value("${security.token.validityInMilliseconds}")
    private long validityInMilliseconds;

    public String createToken(String email, String role) {
        Date expiresAt = new Date(new Date().getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(email)
                .claim(authoritiesKey, role)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(expiresAt)
                .compact();
    }

    public Optional<String> resolveToken(HttpServletRequest request) {
        String token = request.getHeader(authorizationKey);
        if (token != null && token.startsWith(tokenPrefix)) {
            return Optional.of(token.substring(7));
        }
        return Optional.empty();
    }

    public Authentication getAuthentication(String token) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            String username = jws.getBody().getSubject();

            var authorities = Arrays.stream(
                    jws.getBody().get(authoritiesKey)
                            .toString()
                            .split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(username, token, authorities);
        }
        catch (JwtException | IllegalArgumentException ex) {
            throw new InvalidJwtTokenException();
        }
    }

}
