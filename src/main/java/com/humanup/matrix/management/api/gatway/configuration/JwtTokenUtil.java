package com.humanup.matrix.management.api.gatway.configuration;

import com.humanup.matrix.management.api.gatway.vo.AccountVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtTokenUtil implements Serializable {
    @Value("${jwt.security.key}")
    private String jwtKey;
    public String doGenerateToken(String subject) {
        Claims claims = Jwts.claims().setSubject(subject);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://jwtdemo.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +
                        60*60*1000))
                .signWith(SignatureAlgorithm.HS256, jwtKey)
                .compact();
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final Claims claims = getAllClaimsFromToken(token);
        return (
                claims.getSubject().equals(userDetails.getUsername())
                        && !claims.getExpiration().before(new Date()));
    }
}