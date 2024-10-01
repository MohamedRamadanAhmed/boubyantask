package com.boubyan.boubyantask.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String accessToken = jwtTokenProvider.getAuthTokenFromRequest(request);
            if (accessToken == null) {
                filterChain.doFilter(request, response);
                return;
            }
            if (jwtTokenProvider.isTokenNotExpired(accessToken)) {
                String email = jwtTokenProvider.getClaimValue(accessToken, "email");
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(email, "", new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().println("FORBIDDEN: token expired");
                log.info("FORBIDDEN: token expired");
                return;
            }
        } catch (Exception e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().println("FORBIDDEN: token not valid");
            log.info("FORBIDDEN: token not valid");

            return;
        }
        filterChain.doFilter(request, response);
    }
}
