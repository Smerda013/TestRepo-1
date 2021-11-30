package com.greenfox.springadvanced.Security;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;

import io.jsonwebtoken.Claims;

@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration (String token) {
        return  extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim (String token, Funtion<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }






}
