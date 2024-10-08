package com.example.hust_learning_server.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@Service
public class GetPreSignedUrlUtils {

    @Value("${service.host}")
    private   String SERVICE_HOST;

    @Value("${server.ip}")
    private String IP_ADDRESS;

    public  String getPreSignedUrl(String originalUrl) throws UnknownHostException {



        String modifiedUrl = originalUrl.replace(SERVICE_HOST,IP_ADDRESS);

        modifiedUrl = modifiedUrl.split("\\?")[0];

        return modifiedUrl;

    }
}
