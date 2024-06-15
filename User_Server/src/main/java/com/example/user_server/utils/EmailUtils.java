package com.example.user_server.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

public class EmailUtils {

    public static final String ADMIN_EMAIL = "phamquynhltbn12@gmail.com";

    public static final String ADMIN_USER_NAME = "admin@gmail.com";

    public static String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!ObjectUtils.isEmpty(authentication) && authentication.getPrincipal() != null) {
            String email = (String) authentication.getPrincipal();
            if (!email.equals("anonymousUser")) {
                return email;
            }
        }

        return null;
    }
}
