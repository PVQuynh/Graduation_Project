package com.example.hust_learning_server.utils;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

public class EmailUtils {
    public static String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!ObjectUtils.isEmpty(authentication) && authentication.getPrincipal() != null) {
            String email = (String) authentication.getPrincipal();
            if (!email.equals("anonymousUser")) {
                return email;
            }
        }

        return "";
    }

    public static String getRoleOfCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);
        }
        return null;
    }
}
