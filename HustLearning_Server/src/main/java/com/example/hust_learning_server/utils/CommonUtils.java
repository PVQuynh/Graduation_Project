package com.example.hust_learning_server.utils;

import org.apache.logging.log4j.util.Strings;

public class CommonUtils {

    public static int convertPrivate(String isPrivate) {
        if (Strings.isNotBlank(isPrivate)) {
            if (isPrivate.toLowerCase().equals(Boolean.TRUE.toString())) {
                 return  1;
            }
            if (isPrivate.toLowerCase().equals(Boolean.FALSE.toString())) {
                 return  0;
            }
        }
        return -1;
    }

    public static int convertPrivateWithRole(String isPrivate, String role) {
        if (Strings.isNotBlank(role) && role.equals("USER")) {
            return 0;
        }
        if (Strings.isNotBlank(isPrivate)) {
            if (isPrivate.toLowerCase().equals(Boolean.TRUE.toString())) {
                return  1;
            }
            if (isPrivate.toLowerCase().equals(Boolean.FALSE.toString())) {
                return  0;
            }
        }
        return -1;
    }
}
