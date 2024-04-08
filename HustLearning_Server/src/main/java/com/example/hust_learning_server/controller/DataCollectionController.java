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

    @GetMapping("/options-list-me")
    public MessageResponse getOptionsListMe(
            @RequestParam(required = true) int status
    ){
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = dataCollectionService.getOptionsListMe(status);
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

    @GetMapping ("/search-for-me/v2")
    public MessageResponse getDataForUser_v2(
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
        try {
            ms.data = dataCollectionService.searchDataCollectionForUser_v2(page, size, topic, vocabulary, ascending, orderBy, createdFrom, createdTo, status, score);
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;

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
    @GetMapping("/options-list-admin")
    public MessageResponse getOptionsListAdmin(
            @RequestParam(required = true) int status
    ){
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = dataCollectionService.getOptionsListAdmin(status);
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

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

    @GetMapping ("/search-for-admin/v2")
    public MessageResponse getDataForAdmin_v2(
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
        try {
            ms.data = dataCollectionService.searchDataCollectionForAdmin_v2(page, size, volunteerEmail, topic, vocabulary, ascending, orderBy, createdFrom, createdTo, status, score);
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;

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
