package com.example.data_collection_server.service;

import java.util.List;

public interface UploadService {

    String uploadFile(String name, byte[] content);

    List<String> getAllBucket();
}
