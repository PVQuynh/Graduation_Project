package com.example.data_collection_server.service;

import com.example.data_collection_server.dto.response.VocabularyRes;

import java.util.List;

public interface UploadVocabularyService {

    VocabularyRes uploadFile(String name, byte[] content);

    byte[] getFile(String key);

    List<String> getAllFile();

    boolean deleteFile(String fileName);
}
