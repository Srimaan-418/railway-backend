package com.railway.reservation_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter authFilter;

    @Autowired
    private UserDetailsService userDetailsService; // Let Spring inject our UserInfoUserDetailsService here

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                		.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                		.requestMatchers("/api/auth/register", "/api/auth/login", "/api/trains/search").permitAll()
                        .requestMatchers("/api/trains/**").authenticated()
                        .requestMatchers("/api/stations/**").authenticated()
                        .requestMatchers("/api/trains/**", "/api/stations/**", "/api/schedules/**", "/api/bookings/**").authenticated()
                        .requestMatchers("/api/trains/**", "/api/stations/**", "/api/schedules/**").authenticated()
                        .requestMatchers("/api/auth/register", "/api/auth/login", "/api/trains/search").permitAll() // ADD SEARCH HERE
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()) // This call is now valid again
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService); // Use the autowired field
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}