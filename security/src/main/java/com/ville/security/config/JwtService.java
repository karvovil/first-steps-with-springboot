package com.ville.security.config;

import java.security.Key;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

  private static final String SECRET_KEY = "3f5270c71bf32dd13bebd9eb55e45d5e86061b0647aed0461437807877ebe335";

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token){
    return Jwts
      .parserBuilder()
      .setSigningKey(getSigningKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

}
