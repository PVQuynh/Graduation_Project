package com.example.HustLearning.security;


import com.example.HustLearning.dto.KeycloaksInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component

@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
 try {

     String email = getEmailFromRequest(request);
     if (!ObjectUtils.isEmpty(email)){
         UsernamePasswordAuthenticationToken authenticationToken =
                 new UsernamePasswordAuthenticationToken(email, null, null);
         SecurityContextHolder.getContext().setAuthentication(authenticationToken);
     }
 } catch (Exception ex) {
     log.error("Failed to set user authentication",ex);
 }
        filterChain.doFilter(request, response);
    }

    public String getEmailFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken =  bearerToken.substring(7);
            String payload = bearerToken.split("\\.")[1];
            byte[] decodedBytes = Base64.getDecoder().decode(payload);
            String abc = new String(decodedBytes, StandardCharsets.UTF_8);
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                KeycloaksInfo userInfo = objectMapper.readValue(abc, KeycloaksInfo.class);
                return userInfo.getEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       return  null;
    }
}
