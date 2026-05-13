package uz.mahliyoedu.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    // Token imzolash uchun maxfiy kalit
    private static final String SECRET_KEY = "mahliyoedu-secret-key-2024-very-long-string-for-security";

    // Token amal qilish muddati — 24 soat
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

    // Maxfiy kalitni SecretKey obyektiga aylantirish
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Token yaratish — 0.12.6 API
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
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
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // Token ichidagi ma'lumotlarni olish — 0.12.6 API
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
