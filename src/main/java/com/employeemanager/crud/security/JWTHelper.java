package com.employeemanager.crud.security;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JWTHelper {
    // requirement :
    private static final long EXPIRATION_TIME_LIMIT = 24 * 60 * 60 * 1000;

    SecretKey key = Jwts.SIG.HS256.key().build(); // or HS384.key() or HS512.key()

    private String generateToken(String sub, String role) {
        return Jwts.builder()
                .subject(sub)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + EXPIRATION_TIME_LIMIT))
                .signWith(key)
                .compact();
    }

    public String generateToken(UserDetails user) {
        // fetch authority/role from user details
        // SimpleGrantedAuthority role = (SimpleGrantedAuthority)
        // user.getAuthorities().toArray()[0];

        return Jwts.builder()
                // .claim("role", role.getAuthority())
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + EXPIRATION_TIME_LIMIT))
                .signWith(key)
                .compact();
    }

    public String fetchUserName(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

    // public String fetchRole(String token) {
    // return
    // Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().get("role",
    // String.class);
    // }

    // private String fetchPassword(String token) {
    // return
    // Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().get("password",
    // String.class);
    // }

    private Date fetchExpirationDate(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = fetchExpirationDate(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, UserDetails user) {
        return user.getUsername().equals(fetchUserName(token))
                && !isTokenExpired(token);
    }
}
