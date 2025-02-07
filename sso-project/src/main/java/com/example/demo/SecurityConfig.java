package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, RelyingPartyRegistrationRepository registrations) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
            )
            .saml2Login(saml2 -> saml2
                .relyingPartyRegistrationRepository(registrations)
            )
            .saml2Logout(logout -> logout
                .logoutUrl("/")
            );
        
        return http.build();
    }
}