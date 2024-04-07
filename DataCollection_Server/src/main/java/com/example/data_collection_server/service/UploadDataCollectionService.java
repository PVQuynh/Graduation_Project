package com.example.data_collection_server.service;

import java.util.List;

public interface UploadDataCollectionService {

    String uploadFile(String name, byte[] content);

    byte[] getFile(String key);

    List<String> getAllFile();

    boolean deleteFile(String fileName);
}
