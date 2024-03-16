package com.example.data_collection_server.controller;


import com.example.data_collection_server.dto.request.DataDeleteReq;
import com.example.data_collection_server.dto.response.MessageResponse;
import com.example.data_collection_server.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/upload-datas")
@RequiredArgsConstructor
public class UploadDataController {
   private final UploadService uploadService;

    @GetMapping("/all")
    public MessageResponse getAllData() {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = uploadService.getAllFileInBucket();
        } catch (Exception ex) {
            ms.code = 500;
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @GetMapping(path = "/buckets-name")
    public MessageResponse bucketsList() {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = uploadService.getAllBucket();
        } catch (Exception ex) {
            ms.code = 500;
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @GetMapping(path = "/download")
    public MessageResponse uploadFile(@RequestParam(value = "file") String file) throws IOException {
        MessageResponse ms = new MessageResponse();
        try {
            byte[] data = uploadService.getFile(file);
            ByteArrayResource resource = new ByteArrayResource(data);
            ms.data = resource;
        } catch (Exception ex) {
            ms.code = 500;
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadFile(@RequestPart(value = "file", required = false) MultipartFile files) throws IOException {
        return uploadService.uploadFile(files.getOriginalFilename(), files.getBytes());
    }

    @DeleteMapping
    public MessageResponse deleteData(@RequestBody DataDeleteReq dataDeleteReq) {
        MessageResponse ms = new MessageResponse();
        try {
            if (!uploadService.deleteFileInBucket(dataDeleteReq.getFileName())) {
                ms.code = 400;
                ms.message = "There are no files to delete!";
            }
        } catch (Exception ex) {
            ms.code = 500;
            ms.message = ex.getMessage();
        }
        return ms;
    }
}
