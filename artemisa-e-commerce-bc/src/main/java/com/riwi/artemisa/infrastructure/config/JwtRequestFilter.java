package com.riwi.artemisa.infrastructure.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull;
import java.util.ArrayList;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtRequestFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String jwtToken = jwtService.extractJwtFromRequest(request);

        if (jwtToken != null) {
            Claims claims = jwtService.decodeJwt(jwtToken);
            if (claims != null) {
                String name = jwtService.extractName(claims);
                String id = jwtService.extractId(claims);
                String roleUser = jwtService.extractRoleUser(claims);
                List<Map<String, Object>> permissions = jwtService.extractPermissions(claims);

                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                
                // Añadir el rol principal sin el prefijo "ROLE_"
                authorities.add(new SimpleGrantedAuthority(roleUser.toUpperCase()));

                // Añadir los permisos adicionales con el prefijo "ROLE_"
                authorities.addAll(permissions.stream()
                    .map(permission -> new SimpleGrantedAuthority("ROLE_" + permission.get("role").toString().toUpperCase()))
                    .collect(Collectors.toList()));

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(name, null, authorities);

                authenticationToken.setDetails(id);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                request.setAttribute("userId", id);
                request.setAttribute("userPermissions", permissions);
            }
        }

        filterChain.doFilter(request, response);
    }
}