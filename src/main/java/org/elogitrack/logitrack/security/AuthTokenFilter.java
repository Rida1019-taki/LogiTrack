package org.elogitrack.logitrack.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthTokenFilter(
            JwtUtil jwtUtil,
            UserDetailsService userDetailsService
    ) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            System.out.println("Authorization Header Missing");
            filterChain.doFilter(request, response);
            return;
        }

        if (!authHeader.toLowerCase().startsWith("bearer ")) {
            System.out.println("Authorization Header Invalid");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {

            boolean valid = jwtUtil.validateJwtToken(token);

            System.out.println("Token Valid : " + valid);

            if (valid) {

                String username = jwtUtil.getUserFromToken(token);

                System.out.println("Username : " + username);

                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(username);

                System.out.println("EMAIL : " + userDetails.getUsername());
                System.out.println("ROLES : " + userDetails.getAuthorities());

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("Authentication SUCCESS");
            }

        } catch (Exception e) {

            System.out.println("JWT ERROR : " + e.getMessage());

        }

        filterChain.doFilter(request, response);
    }
}