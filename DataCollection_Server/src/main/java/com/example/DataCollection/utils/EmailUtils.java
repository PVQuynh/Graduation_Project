package com.example.DataCollection.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

public class EmailUtils {
    public static String getCurrentUser() {
        String email = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!ObjectUtils.isEmpty(authentication)) {
            email = (String) authentication.getPrincipal();
        }
        return email;
    }
}
