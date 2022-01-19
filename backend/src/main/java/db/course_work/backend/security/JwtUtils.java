package db.course_work.backend.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import db.course_work.backend.entities.Member;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import java.util.Date;

@Slf4j
@Component
public class JwtUtils {
    private String jwtSecret = "VorovatPloho";
    private int jwtExpirationMs = 100000000;

    public String generateJwtToken(Authentication authentication) {

        Member userPrincipal = (Member) authentication.getPrincipal();

        return Jwts.builder().setSubject((userPrincipal.getLogin())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
//            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
//            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
//            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
//            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
//            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}