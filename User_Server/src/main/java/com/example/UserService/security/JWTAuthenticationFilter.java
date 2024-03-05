package com.example.UserService.security;


import com.example.UserService.entity.KeycloaksInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JWTService jwtService;
    @Autowired
    UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
 try {

     KeycloaksInfo  keycloaksInfo = getKeyCloaksInfoFromRequest(request);
     String email = keycloaksInfo.getEmail();
     if (!ObjectUtils.isEmpty(email)){
         KeycloaksInfo.RealmAccess roleRealmAccess = keycloaksInfo.getRealmAccess();
         String  role = roleRealmAccess.getRoles().get(3);

         Set<GrantedAuthority> grantedAuthority = Collections.singleton(new GrantedAuthority() {
             @Override
             public String getAuthority() {
                 return role;
             }
         });
         UsernamePasswordAuthenticationToken authenticationToken =
                 new UsernamePasswordAuthenticationToken(email, null, grantedAuthority);

         SecurityContextHolder.getContext().setAuthentication(authenticationToken);
     }
 } catch (Exception ex) {
     log.error("Failed to set user authentication",ex);
 }
        filterChain.doFilter(request, response);
    }

    public KeycloaksInfo getKeyCloaksInfoFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken =  bearerToken.substring(7);
            String payload = bearerToken.split("\\.")[1];
            byte[] decodedBytes = Base64.getDecoder().decode(payload);
            String abc = new String(decodedBytes, StandardCharsets.UTF_8);
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                KeycloaksInfo userInfo = objectMapper.readValue(abc, KeycloaksInfo.class);
                return userInfo;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       return  null;
    }
}
