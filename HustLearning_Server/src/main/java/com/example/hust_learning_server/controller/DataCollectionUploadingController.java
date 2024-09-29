package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.*;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.DataCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v2/data-collection")
@RequiredArgsConstructor
public class DataCollectionUploadingController {
    private final DataCollectionService dataCollectionService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MessageResponse> uploadGeneralMediaFile(
            @RequestPart(value = "file") MultipartFile file,
            @RequestParam long vocabularyId,
            @RequestParam(required = false) String detectionContent
    ) throws IOException {
        MessageResponse ms = new MessageResponse();
        dataCollectionService.sendData(vocabularyId, detectionContent, file);
        return ResponseEntity.ok(ms);
    }

    // Admin
    @PostMapping("/approve")
    public ResponseEntity<MessageResponse> approveData(@RequestBody DataReq dataReq) throws Exception {
        MessageResponse ms = new MessageResponse();
        dataCollectionService.approveV2(dataReq);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/reject")
    public ResponseEntity<MessageResponse> rejectData(@RequestBody DataReq dataReq) throws Exception {
        MessageResponse ms = new MessageResponse();
        dataCollectionService.rejectV2(dataReq);
        return ResponseEntity.ok(ms);
    }

}
