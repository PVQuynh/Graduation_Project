package com.example.data_collection_server.controller;


import com.example.data_collection_server.dto.response.MessageResponse;
import com.example.data_collection_server.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UploadDataController {
   private final UploadService uploadService;

    @GetMapping(path = "/buckets-name")
    public MessageResponse bucketsList() {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = uploadService.getAllBucket();
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String upload(@RequestPart(value = "file", required = false) MultipartFile files) throws IOException {
        return uploadService.uploadFile(files.getOriginalFilename(), files.getBytes());
    }







}
