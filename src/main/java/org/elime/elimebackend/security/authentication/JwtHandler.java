package org.elime.elimebackend.security.authentication;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.elime.elimebackend.data.entities.User;
import org.elime.elimebackend.data.enumerators.Token;
import org.elime.elimebackend.security.exception.customException.TokenException;
import org.elime.elimebackend.security.exception.customException.TokenExpiredException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtHandler {

    //todo @Value("${jwt.secret}")
    private String secret;


    public String createAccessToken(User user) {
        return createToken(user, 15, ChronoUnit.MINUTES, Token.ACCESS);
    }


    public String createRefreshToken(User user) {
        return createToken(user, 365, ChronoUnit.DAYS, Token.REFRESH);
    }


    private String createToken(User user, long amountToAdd, ChronoUnit unit, Token tokenType) {
        Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issueTime(Date.from(issuedAt))
                .expirationTime(Date.from(issuedAt.plus(amountToAdd, unit)))
                .claim("type", tokenType)
                .claim("role", user.getRole().name())
                .build();

        try {
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);
            signedJWT.sign(new MACSigner(secret.getBytes()));
            return signedJWT.serialize();
        } catch (Exception e) {
            throw new TokenException("Errore durante la generazione del " + tokenType.name().toLowerCase() + " token");
        }
    }


    public String getJwtFromRequest(HttpServletRequest request, Token token) {
        String header = "";
        String starts = "";

        if(token == Token.ACCESS){
            header = request.getHeader(HttpHeaders.AUTHORIZATION);
            starts = "Bearer";
        } else if(token == Token.REFRESH){
            header = request.getHeader("X-Refresh-Token");
        }

        if (header != null && header.startsWith(starts)) {
            return header.substring(starts.length()).trim();
        }

        return "invalid";
    }


    public boolean isValidAccessToken(String token) {
        return isValidToken(token, Token.ACCESS);
    }


    public boolean isValidRefreshToken(String token) {
        return isValidToken(token, Token.REFRESH);
    }


    private boolean isValidToken(String token, Token tokenType) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            if(!signedJWT.verify(new MACVerifier(secret.getBytes()))) return false;

            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            Date expirationTime = claims.getExpirationTime();
            String type = claims.getStringClaim("type");

            if(expirationTime == null) return false;
            return tokenType.name().equals(type) && expirationTime.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }


    public String getEmailFromToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            return claims.getSubject();
        } catch (Exception e) {
            throw new TokenExpiredException("Token non valido");
        }
    }
}
