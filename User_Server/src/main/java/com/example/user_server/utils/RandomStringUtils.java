package com.example.user_server.utils;

import java.util.Random;

public class RandomStringUtils {

    public static String generateRandomString(int num) {
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[{]}|;:',<.>/?";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";

        StringBuilder stringBuilder = new StringBuilder();

        if (num > 0 && num <= characters.length()) {
            for (int i = 0; i <num; i++) {
                Character character = characters.charAt(new Random().nextInt(characters.length()));
                stringBuilder.append(character);
            }

            return stringBuilder.toString();
        }

        return "";
    }
}
