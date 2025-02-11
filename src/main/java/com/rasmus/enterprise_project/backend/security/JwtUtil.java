package com.rasmus.enterprise_project.backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private final String base64EncodedSecretKey = "U2VjdXJlQXBpX1NlY3JldEtleV9mb3JfSFMyNTYwX3NlY3JldF9wcm9qZWN0X2tleV9leGFtcGxl";
    private final SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64EncodedSecretKey));
    private final int jwtExpirationMs = (int) TimeUnit.HOURS.toMillis(1);

    public void setJwtCookie(HttpServletResponse response, String username) {
        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key)
                .compact();

        response.setHeader("Set-Cookie", "token=" + token + "; HttpOnly; Secure; SameSite=Strict; Max-Age=" + jwtExpirationMs / 1000);
    }
}
