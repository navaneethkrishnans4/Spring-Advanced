package com.bej.muzixservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Extract the token from the Authorization header
        String token = request.getHeader("Authorization");

        // Check if the token exists and starts with "Bearer "
        if (token != null && token.startsWith("Bearer ")) {
            try {
                // Remove "Bearer " prefix from the token
                String jwtToken = token.substring(7);

                // Parse the token and extract claims
                Claims claims = Jwts.parser()
                        .setSigningKey("secret_key") // Replace with your actual secret key
                        .parseClaimsJws(jwtToken)
                        .getBody();

                // Extract the user ID from claims
                String userId = claims.getSubject();

                // Set the user ID as an attribute in the request header
                request.setAttribute("userId", userId);
            } catch (Exception e) {
                // Token parsing failed, return unauthorized response
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
        } else {
            // Token is missing or malformed, return unauthorized response
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or malformed token");
            return;
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
    }

