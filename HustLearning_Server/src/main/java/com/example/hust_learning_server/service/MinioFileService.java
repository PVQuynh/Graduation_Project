package com.example.hust_learning_server.service;

import java.util.List;

public interface MinioFileService {

    String uploadFile(String fileName, byte[] content);

    byte[] getFile(String fileName);

    List<String> getAllFile();

    boolean deleteFile(String fileName);
}
