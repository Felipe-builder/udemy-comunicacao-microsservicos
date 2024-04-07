package br.com.cursoudemy.productapi.modules.jwt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.exceptions.AuthenticationException;
import br.com.cursoudemy.productapi.modules.jwt.dto.JwtResponse;
import br.com.cursoudemy.productapi.modules.jwt.mapper.JwtMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import static org.springframework.util.ObjectUtils.isEmpty;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    private static final String EMPTY_SPACE = " ";
    private static final Integer TOKEN_INDEX = 1;

    @Value("${app-config.secrets.api-secret}")
    private String apiSecret;

    public void validateAuthorization(String token) {
        try {
            String accessToken = extractToken(token);
            SecretKey secretKey = Keys.hmacShaKeyFor(apiSecret.getBytes());

            Claims claims = Jwts
                    .parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload();

            JwtResponse user = JwtMapper.getUser(claims);
            if (isEmpty(user) || isEmpty(user.getId())) {
                throw new AuthenticationException("The user is not valid");
            }
        }  catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String extractToken(String token) {
        if (isEmpty(token)) {
            throw new AuthenticationException("The access token was not informed");
        }
        if (token.contains(EMPTY_SPACE)) {
            token = token.split(EMPTY_SPACE)[TOKEN_INDEX];
        }
        return token;
    }
}
