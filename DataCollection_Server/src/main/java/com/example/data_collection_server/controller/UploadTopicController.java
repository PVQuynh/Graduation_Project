package com.example.data_collection_server.controller;


import com.example.data_collection_server.dto.request.DataDeleteReq;
import com.example.data_collection_server.dto.response.MessageResponse;
import com.example.data_collection_server.service.UploadDataCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload-topics")
@RequiredArgsConstructor
public class UploadTopicController {
   private final UploadDataCollectionService uploadDataCollectionService;

    @GetMapping("/all")
    public MessageResponse getAllData() {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = uploadDataCollectionService.getAllFile();
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
            byte[] data = uploadDataCollectionService.getFile(file);
            ByteArrayResource resource = new ByteArrayResource(data);
            ms.data = resource;
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public MessageResponse uploadGeneralMediaFile(@RequestPart(value = "file", required = false) MultipartFile files) throws IOException {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = uploadDataCollectionService.uploadFile(files.getOriginalFilename(), files.getBytes());
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
            if (!uploadDataCollectionService.deleteFile(dataDeleteReq.getFileName())) {
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
