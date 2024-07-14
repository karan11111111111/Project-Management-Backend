package com.projectManagement.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class AppConfig {

    // Configure security filter chain
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Session management configuration
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Authorization rules for endpoints
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/**").authenticated()  // Secure API endpoints
                        .anyRequest().permitAll()  // Allow all other requests
                )

                // Add JWT token validator filter before BasicAuthenticationFilter
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)

                // Disable CSRF (Cross-Site Request Forgery) protection
                .csrf(csrf -> csrf.disable())

                // Configure CORS (Cross-Origin Resource Sharing)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // Build the HTTP security configuration
                .build();
    }

    // CORS configuration source bean
    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration corsConfig = new CorsConfiguration();

                // Allowed origins
                corsConfig.setAllowedOrigins(Arrays.asList(
                        "http://localhost:8081",
                        "http://localhost:8082",
                        "http://localhost:5173"

                ));

                // Allowed methods (GET, POST, etc.)
                corsConfig.setAllowedMethods(Collections.singletonList("*"));

                // Allow credentials (e.g., cookies)
                corsConfig.setAllowCredentials(true);

                // Allowed headers
                corsConfig.setAllowedHeaders(Collections.singletonList("*"));

                // Exposed headers (headers that the client can access)
                corsConfig.setExposedHeaders(Arrays.asList("Authorization"));

                // Max age of preflight request
                corsConfig.setMaxAge(3600L);

                return corsConfig;
            }
        };
    }

    // Password encoder bean (for encoding and verifying passwords)
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
