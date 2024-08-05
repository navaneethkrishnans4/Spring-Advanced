package com.bej.authentication.security;


import com.bej.authentication.domain.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    @Override
    public String createToken(Customer customer) {

        // Write logic to create the Jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("customerId", customer.getCustomerId());
        return generateToken(claims,customer.getCustomerId());
        }

        public String generateToken(Map<String,Object> claims,int subject) {
            // Generate and return the token
            String jwtToken = Jwts.builder().setIssuer("ProductZone")
                    .setClaims(claims)
                    .setSubject(String.valueOf(subject))
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256,"mysecret")
                    .compact();
            return jwtToken;

        }
    }

