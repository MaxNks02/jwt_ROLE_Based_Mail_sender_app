package com.example.jwtexample.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    String secret = "MaxfiySo'z"; //xoxlagan bo'lishi mn
    long expireTime = 86400 * 1000; //millisekund


    public String generateToken(String userName) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512,secret)
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .compact();
    }

    //jwt ni expire date
    public boolean expireToken(String token) {
        try {
            Date expiration = Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    //jwt ni validatsiya jwtmi token
    public boolean validateToken(String token) {
        try {
            Jwts.
                    parser().
                    setSigningKey(secret).
                    parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            //xatolik
            return false;
        }
    }

    //jwtdan tizim un kim kirdi?
    public String getUserNameFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


}
