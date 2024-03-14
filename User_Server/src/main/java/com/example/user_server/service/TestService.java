package com.example.user_server.service;

import com.example.user_server.entity.KeycloaksInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class TestService {
    public KeycloaksInfo test(String text) {
        byte[] decodedBytes = Base64.getDecoder().decode(text);
        String abc = new String(decodedBytes, StandardCharsets.UTF_8);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            KeycloaksInfo userInfo = objectMapper.readValue(abc, KeycloaksInfo.class);

            return userInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
