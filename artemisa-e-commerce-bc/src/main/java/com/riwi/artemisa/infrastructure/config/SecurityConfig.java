package com.riwi.artemisa.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/v1/api/category/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/v1/api/category/user/**").hasAnyAuthority("ADMIN", "TUTOR")
                
                .requestMatchers("/v1/api/media/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/v1/api/media/user/**").hasAnyAuthority("ADMIN", "TUTOR")

                .requestMatchers("/v1/api/medication/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/v1/api/medication/user/**").hasAnyAuthority("ADMIN", "TUTOR")

                .requestMatchers("/v1/api/medicationInventory/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/v1/api/medicationInventory/user/**").hasAnyAuthority("ADMIN", "TUTOR")
                
                .requestMatchers("/v1/api/order/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/v1/api/order/user/**").hasAnyAuthority("ADMIN", "TUTOR")
                
                .requestMatchers("/v1/api/petshopPayments/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/v1/api/petshopPayments/user/**").hasAnyAuthority("ADMIN", "TUTOR")
                
                .requestMatchers("/v1/api/productInventory/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/v1/api/productInventory/user/**").hasAnyAuthority("ADMIN", "TUTOR")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(new JwtRequestFilter(jwtService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
