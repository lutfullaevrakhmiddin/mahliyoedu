package uz.mahliyoedu.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // Token imzolash uchun maxfiy kalit — production da application.properties ga ko'chirish kerak
    private static final String SECRET_KEY = "mahliyoedu-secret-key-2024-very-long-string-for-security";

    // Token amal qilish muddati — 24 soat
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

    // Maxfiy kalitni Key obyektiga aylantirish
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Token yaratish — admin login qilganda chaqiriladi
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Tokendan email olish
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // Token haqiqiyligini tekshirish
    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractClaims(token);
            // Token muddati o'tmagan bo'lishi kerak
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // Token ichidagi ma'lumotlarni olish
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
