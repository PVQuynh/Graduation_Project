package com.example.data_collection_server.service;

import com.example.data_collection_server.dto.response.VocabularyRes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UploadVocabularyService {

    VocabularyRes uploadFile(String name, byte[] content);

    List<VocabularyRes> uploadFileList(List<MultipartFile> files) throws IOException;

    byte[] getFile(String key);

    List<String> getAllFile();

    boolean deleteFile(String fileName);
}
