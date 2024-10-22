package org.example;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;

public class JwtTokenProvider {
    private final String secretKey="gbidonswr43423234234edfgdfgdfgdfgdfgdfgdfgdfgfe";


    public String createToken(String userId,int tokenValidityInHour){
        long tokenValidityInSeconds=3600000;
        Date now = new Date();
        Date validity = new Date(now.getTime()+tokenValidityInHour*tokenValidityInSeconds);
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
    public String getUserIdFromToken(String token){
        Claims claims=Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expiration.after(new Date());
    }
}
