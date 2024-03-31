package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.*;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.dto.response.SearchDataRes;
import com.example.hust_learning_server.service.DataCollectionService;
import java.text.ParseException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data-collection")
@RequiredArgsConstructor
public class DataCollectionController {
    private final DataCollectionService dataCollectionService;


    // User
    @GetMapping("/all-me")
    public MessageResponse getAllMe(){
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = dataCollectionService.getAllMe();
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @GetMapping("/pending-list-me")
    public MessageResponse getPendingMe(){
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = dataCollectionService.getPendingMe();
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @GetMapping("/approved-list-me")
    public MessageResponse getApprovedMe(){
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = dataCollectionService.getApprovedMe();
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @GetMapping("/reject-list-me")
    public MessageResponse getRejectMe(){

        MessageResponse ms = new MessageResponse();
        try {
            ms.data = dataCollectionService.getRejectMe();
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping ("/search-for-me")
    public PageDTO<SearchDataRes> getDataForUser(@RequestBody DataSearchForUserParam dataSearchForUserParam)
            throws ParseException {

        return dataCollectionService.searchDataCollectionForUser(dataSearchForUserParam);

    }

    @PostMapping
    public MessageResponse sendData(@RequestBody DataProvideReq dataProvideReq) {
        MessageResponse ms = new MessageResponse();
        try {
            dataCollectionService.sendData(dataProvideReq);
        } catch (Exception e) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PutMapping
    public MessageResponse updateData(@RequestBody UpdateDataReq updateDataReq) {
        MessageResponse ms = new MessageResponse();
        try {
            dataCollectionService.updateData(updateDataReq);
        } catch (Exception e) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }


    // Admin
    @GetMapping("/pending-list-admin")
    public MessageResponse getPendingAdmin(){

        MessageResponse ms = new MessageResponse();
        try {
            ms.data = dataCollectionService.getPendingAdmin();
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping ("/search-for-admin")
    public PageDTO<SearchDataRes> getDataForAdmin(@RequestBody DataSearchForAdminParam dataSearchForAdminParam)
        throws ParseException {

        return dataCollectionService.searchDataCollectionForAdmin(dataSearchForAdminParam);

    }

    @PostMapping("/approve")
    public MessageResponse approveData(@RequestBody DataReq dataReq) {
        MessageResponse ms = new MessageResponse();
        try {
            dataCollectionService.approve(dataReq);
        } catch (Exception e) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping("/reject")
    public MessageResponse rejectData(@RequestBody DataReq dataReq){
        MessageResponse ms = new MessageResponse();
        try {
            dataCollectionService.reject(dataReq);
        }
        catch (Exception exception) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteData(@PathVariable long id){
        MessageResponse ms = new MessageResponse();
        try {
            dataCollectionService.delete(id);
        }
        catch (Exception exception) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

}
