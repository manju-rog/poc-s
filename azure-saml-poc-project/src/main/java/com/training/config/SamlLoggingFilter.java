package com.training.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SamlLoggingFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(SamlLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String samlResponse = request.getParameter("SAMLResponse");

        if (samlResponse != null) {
            logger.info("********************SAML Response: {}", new String(java.util.Base64.getDecoder().decode(samlResponse)));
        }

        filterChain.doFilter(request, response);
    }
}