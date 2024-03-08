package com.example.DataCollection.controller;


import com.example.DataCollection.dto.request.DataDeleteReq;
import com.example.DataCollection.dto.response.MessagesResponse;
import com.example.DataCollection.service.UploadService;
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
    public List<String> getAllData() {
        return uploadService.getAllFileInBucket();
    }

    @GetMapping(path = "/buckets-name")
    public List<String> bucketsList() {
        return uploadService.getAllBucket();
    }

    @GetMapping(path = "/download")
    public MessagesResponse uploadFile(@RequestParam(value = "file") String file) throws IOException {
        MessagesResponse ms = new MessagesResponse();
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
    public MessagesResponse delateData(@RequestBody DataDeleteReq dataDeleteReq) {
        MessagesResponse ms = new MessagesResponse();
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
