package com.projectManagement.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Extract JWT token from the request header
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        // Check if JWT token exists and starts with 'Bearer '
        if (jwt != null) {
            jwt = jwt.substring(7); // Remove 'Bearer ' prefix from token

            try {
                // Generate secret key using HMAC SHA algorithm from the secret key constant
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRETE_KEY.getBytes());

                // Parse JWT claims from the token
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                // Extract email and authorities from JWT claims
                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));

                // Convert authorities string to a list of GrantedAuthority objects
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                // Create authentication object with email, null credentials, and authorities
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);

                // Set the authentication object in the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                // Throw BadCredentialsException for invalid tokens
                throw new BadCredentialsException("Invalid token");
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
