package br.com.cursoudemy.productapi.modules.jwt.mapper;


import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cursoudemy.productapi.modules.jwt.dto.JwtResponse;
import io.jsonwebtoken.Claims;

public class JwtMapper {
    public static JwtResponse getUser(Claims claims) {

        try {
            return new ObjectMapper().convertValue(claims.get("authUser"), JwtResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
