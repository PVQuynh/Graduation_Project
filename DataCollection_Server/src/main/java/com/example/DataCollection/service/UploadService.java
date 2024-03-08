package com.example.DataCollection.service;

import java.util.List;

public interface UploadService {
    String uploadFile(String name, byte[] content);

    byte[] getFile(String key);

    List<String> getAllBucket();

    List<String> getAllFileInBucket();

    boolean deleteFileInBucket(String fileName);
}
