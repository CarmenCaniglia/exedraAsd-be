package carmencaniglia.exedraAsd.security;

import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${spring.jwt.secret}")
    private String secret;

    public String createToken(Utente user){
        return Jwts.builder().subject(String.valueOf(user.getId())) //soggetto --> id utente
                .issuedAt(new Date(System.currentTimeMillis())) //data emissione
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 24)) //data scadenza
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())) //firmo il token
                .compact();
    }


    public void verifyToken(String token){
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi col token! Per favore effettua di nuovo il login!");
        }
    }

    public String extractIdFromToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token).getPayload().getSubject();
    }
}
