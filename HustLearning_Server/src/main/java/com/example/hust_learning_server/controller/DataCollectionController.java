package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.*;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.dto.response.SearchDataRes;
import com.example.hust_learning_server.service.DataCollectionService;

import java.io.IOException;
import java.text.ParseException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/data-collection")
@RequiredArgsConstructor
public class DataCollectionController {
    private final DataCollectionService dataCollectionService;

    // User
    @GetMapping("/all-me")
    public ResponseEntity<MessageResponse> getAllMe() {
        MessageResponse ms = new MessageResponse();
        ms.data = dataCollectionService.getAllMe();
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/options-list-me")
    public ResponseEntity<MessageResponse> getOptionsListMe(
            @RequestParam(required = true) int status
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = dataCollectionService.getOptionsListMe(status);
        return ResponseEntity.ok(ms);
    }


    @GetMapping("/pending-list-me")
    public ResponseEntity<MessageResponse> getPendingMe() {
        MessageResponse ms = new MessageResponse();
        ms.data = dataCollectionService.getPendingMe();
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/approved-list-me")
    public ResponseEntity<MessageResponse> getApprovedMe() {
        MessageResponse ms = new MessageResponse();
        ms.data = dataCollectionService.getApprovedMe();
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/reject-list-me")
    public ResponseEntity<MessageResponse> getRejectMe() {
        MessageResponse ms = new MessageResponse();
        ms.data = dataCollectionService.getRejectMe();
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/search-for-me")
    public PageDTO<SearchDataRes> getDataForUser(@RequestBody DataSearchForUserParamV3 dataSearchForUserParam)
            throws ParseException {
        return dataCollectionService.searchDataCollectionForUserV3(dataSearchForUserParam);
    }

    @GetMapping("/search-for-me/v2")
    public ResponseEntity<MessageResponse> getDataForUser_v2(
            @RequestParam(defaultValue = "1", required = true) int page,
            @RequestParam(defaultValue = "10", required = true) int size,
            @RequestParam(required = false) String topic,
            @RequestParam(required = true) String vocabulary,
            @RequestParam(required = false) boolean ascending,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String createdFrom,
            @RequestParam(required = false) String createdTo,
            @RequestParam(required = false) int status,
            @RequestParam(required = false) float score
    ) throws ParseException {
        MessageResponse ms = new MessageResponse();
        ms.data = dataCollectionService.searchDataCollectionForUser_v2(page, size, topic, vocabulary, ascending, orderBy, createdFrom, createdTo, status, score);
        return ResponseEntity.ok(ms);

    }

    @PostMapping
    public ResponseEntity<MessageResponse> sendData(@RequestBody DataProvideReq dataProvideReq) {
        MessageResponse ms = new MessageResponse();
        dataCollectionService.sendData(dataProvideReq);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateData(@RequestBody UpdateDataReq updateDataReq) {
        MessageResponse ms = new MessageResponse();
        dataCollectionService.updateData(updateDataReq);
        return ResponseEntity.ok(ms);
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
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
    @GetMapping("/options-list-admin")
    public ResponseEntity<MessageResponse> getOptionsListAdmin(
            @RequestParam(required = true) int status
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = dataCollectionService.getOptionsListAdmin(status);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/pending-list-admin")
    public ResponseEntity<MessageResponse> getPendingAdmin() {
        MessageResponse ms = new MessageResponse();
        ms.data = dataCollectionService.getPendingAdmin();
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/search-for-admin")
    public PageDTO<SearchDataRes> getDataForAdmin(@RequestBody DataSearchForAdminParam dataSearchForAdminParam)
            throws ParseException {
        return dataCollectionService.searchDataCollectionForAdmin(dataSearchForAdminParam);
    }

    @GetMapping("/search-for-admin/v2")
    public ResponseEntity<MessageResponse> getDataForAdmin_v2(
            @RequestParam(defaultValue = "1", required = true) int page,
            @RequestParam(defaultValue = "10", required = true) int size,
            @RequestParam(required = true) String volunteerEmail,
            @RequestParam(required = false) String topic,
            @RequestParam(required = true) String vocabulary,
            @RequestParam(required = false) boolean ascending,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String createdFrom,
            @RequestParam(required = false) String createdTo,
            @RequestParam(required = false) int status,
            @RequestParam(required = false) float score
    ) throws ParseException {
        MessageResponse ms = new MessageResponse();
        ms.data = dataCollectionService.searchDataCollectionForAdmin_v2(page, size, volunteerEmail, topic, vocabulary, ascending, orderBy, createdFrom, createdTo, status, score);
        return ResponseEntity.ok(ms);

    }

    @PostMapping("/approve")
    public ResponseEntity<MessageResponse> approveData(@RequestBody DataReq dataReq) {
        MessageResponse ms = new MessageResponse();
        dataCollectionService.approve(dataReq);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/reject")
    public ResponseEntity<MessageResponse> rejectData(@RequestBody DataReq dataReq) {
        MessageResponse ms = new MessageResponse();
        dataCollectionService.reject(dataReq);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteData(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        dataCollectionService.delete(id);
        return ResponseEntity.ok(ms);
    }

}
