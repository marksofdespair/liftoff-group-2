package org.teamlaika.laikaspetpark;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;


public class JwtGenerator {
    private static byte[] ourSecret = Base64.getDecoder().decode("d6npw1rXVPd+IjWL+2uN3Hv4JcplbZx7cIJ0CMN+p6Y=");

    public static String generateJwt(int userId, String role) {
        Instant now = Instant.now();

        String jwtToken = Jwts.builder()
                .subject(Integer.toString((userId)))
                .claim("role", role)
                .issuedAt(Date.from(now)).expiration(Date.from(now.plus(15, ChronoUnit.MINUTES)))
                .signWith(Keys.hmacShaKeyFor(ourSecret))
                .compact();

        //System.out.println(jwtToken);

        return jwtToken;
    }

    public static Object verifyAndGetUserAndRole(String jwtToken) {


        try {
            Jws<Claims> result = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(ourSecret))
                    .build().parseSignedClaims(jwtToken);


            return true;
        } catch (Exception err) {
            throw new InvalidParameterException("JWT Validation Failed: " + err.getMessage());
        }
    }

    // TODO: Stretch add second jwt generation and db storage for refresh token
}
