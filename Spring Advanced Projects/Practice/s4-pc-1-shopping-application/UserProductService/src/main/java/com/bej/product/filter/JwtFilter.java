package com.bej.product.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String jwtToken = request.getHeader("Authorization");
        if (jwtToken == null || !jwtToken.startsWith("Bearer ")) {
            // Token is missing or invalid, return unauthorized response
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            // Extract claims from the token
            Claims claims = Jwts.parser().setSigningKey("mysecret").parseClaimsJws(jwtToken.substring(7)).getBody();
            // Set the user id from claims in the request header as an attribute
            request.setAttribute("customerId", claims.get("customerId"));
        } catch (Exception e) {
            // Token is invalid, return unauthorized response
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
        }
    }

