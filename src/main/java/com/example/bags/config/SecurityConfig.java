package com.example.bags.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.oauth2ResourceServer(oauth2 -> oauth2.jwt());

        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "**").authenticated()
                .requestMatchers(HttpMethod.PUT, "**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "**").authenticated()
                .requestMatchers(HttpMethod.GET, "**").permitAll()
                .anyRequest().permitAll();

        return http.build();
    }
}
