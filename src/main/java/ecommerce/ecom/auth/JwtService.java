package ecommerce.ecom.auth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String key;

    public JwtService() throws Exception {
        // KeyGenerator keygen = KeyGenerator.getInstance("HmacSHA256");
        // SecretKey skey = keygen.generateKey();
        // key = skey.toString();
    }

    public String generateToken(String username) throws Exception {
        Map<String, ?> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 60 * 60))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() throws Exception {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver)
            throws JwtException, IllegalArgumentException, Exception {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws JwtException, IllegalArgumentException, Exception {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUserName(String token) throws JwtException, IllegalArgumentException, Exception {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails)
            throws JwtException, IllegalArgumentException, Exception {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) throws JwtException, IllegalArgumentException, Exception {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) throws JwtException, IllegalArgumentException, Exception {
        return extractClaim(token, Claims::getExpiration);
    }

}
