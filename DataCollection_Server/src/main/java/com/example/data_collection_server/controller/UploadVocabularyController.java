package com.example.data_collection_server.controller;


import com.example.data_collection_server.dto.request.DataDeleteReq;
import com.example.data_collection_server.dto.response.MessageResponse;
import com.example.data_collection_server.dto.response.VocabularyRes;
import com.example.data_collection_server.service.UploadVocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/upload-vocabularies")
@RequiredArgsConstructor
public class UploadVocabularyController {
   private final UploadVocabularyService uploadVocabularyService;

    @GetMapping("/all-file-name")
    public MessageResponse getAllData() {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = uploadVocabularyService.getAllFile();
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @GetMapping("/all-file-location")
    public MessageResponse getAllFleLocation() {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = uploadVocabularyService.getAllFileLocation();
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @GetMapping(path = "/download")
    public MessageResponse uploadFile(@RequestParam(value = "file") String file) throws IOException {
        MessageResponse ms = new MessageResponse();
        try {
            byte[] data = uploadVocabularyService.getFile(file);
            ByteArrayResource resource = new ByteArrayResource(data);
            ms.data = resource;
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public MessageResponse uploadGeneralMediaFile(@RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = uploadVocabularyService.uploadFile(file.getOriginalFilename(), file.getBytes());
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping(path = "/upload-list", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public MessageResponse uploadGeneralMediaFileList(@RequestPart(value = "files", required = false) List<MultipartFile> files) throws IOException {
        MessageResponse ms = new MessageResponse();
        try {
//            List<VocabularyRes> vocabularyResList = new LinkedList<>();
//            for (MultipartFile file :
//                    files) {
//                vocabularyResList.add(uploadVocabularyService.uploadFile(file.getOriginalFilename(), file.getBytes()));
//            }
//            ms.data = vocabularyResList;

            ms.data = uploadVocabularyService.uploadFileList(files);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }


    @DeleteMapping
    public MessageResponse deleteData(@RequestBody DataDeleteReq dataDeleteReq) {
        MessageResponse ms = new MessageResponse();
        try {
            if (!uploadVocabularyService.deleteFile(dataDeleteReq.getFileName())) {
                ms.code = HttpStatus.NOT_FOUND.value();
                ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
            }
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }
}
