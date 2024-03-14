package com.example.learning_server.controller;

import com.example.learning_server.constant.ExceptionConstant;
import com.example.learning_server.constant.HTTPCode;
import com.example.learning_server.dto.PageDTO;
import com.example.learning_server.dto.request.*;
import com.example.learning_server.dto.response.MessageResponse;
import com.example.learning_server.dto.response.SearchDataRes;
import com.example.learning_server.service.DataCollectionService;
import java.text.ParseException;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collect-data")
@RequiredArgsConstructor
public class CollectDataController {
    private final DataCollectionService dataCollectionService;


    // User
    @GetMapping("/all-me")
    public MessageResponse getAllMe(){
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = dataCollectionService.getAllMe();
        } catch (Exception e) {
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
        }
        return ms;
    }

    @GetMapping("/pending-list-me")
    public MessageResponse getPendingMe(){
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = dataCollectionService.getPendingMe();
        } catch (Exception e) {
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
        }
        return ms;
    }

    @GetMapping("/approved-list-me")
    public MessageResponse getApprovedMe(){
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = dataCollectionService.getApprovedMe();
        } catch (Exception e) {
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
        }
        return ms;
    }

    @GetMapping("/reject-list-me")
    public MessageResponse getRejectMe(){

        MessageResponse ms = new MessageResponse();
        try {
            ms.data = dataCollectionService.getRejectMe();
        } catch (Exception e) {
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
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
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }

    @PutMapping
    public MessageResponse updateData(@RequestBody UpdateDataReq updateDataReq) {
        MessageResponse ms = new MessageResponse();
        try {
            dataCollectionService.updateData(updateDataReq);
        } catch (Exception e) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
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
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
        }
        return ms;
    }

    @PostMapping ("/search-for-admin")
    public PageDTO<SearchDataRes> getDataForAdmin(@RequestBody DataSearchForAdminParam dataSearchForAdminParam)
        throws ParseException {

        return dataCollectionService.searchDataCollectionForAdmin(dataSearchForAdminParam);

    }

    @PostMapping("/approve/{id}")
    public MessageResponse approveData(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        try {
            dataCollectionService.approve(id);
        } catch (Exception e) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }

    @PostMapping("/reject")
    public MessageResponse rejectData(@RequestBody DataRejectReq dataRejectReq){
        MessageResponse ms = new MessageResponse();
        try {
            dataCollectionService.reject(dataRejectReq);
        }
        catch (Exception exception) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
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
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }

}
