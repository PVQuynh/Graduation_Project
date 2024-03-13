package com.example.HustLearning.controller;

import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.*;
import com.example.HustLearning.dto.response.DataCollectionRes;
import com.example.HustLearning.dto.response.MessagesResponse;
import com.example.HustLearning.dto.response.SearchDataRes;
import com.example.HustLearning.service.DataCollectionService;
import java.text.ParseException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collect-data")
@RequiredArgsConstructor
public class CollectDataController {
    private final DataCollectionService dataCollectionService;


    // User
    @GetMapping("/all-me")
    public List<DataCollectionRes> getAllMe(){
        try {
            return dataCollectionService.getAllMe();
        }
        catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/pending-list-me")
    public List<DataCollectionRes> getPendingMe(){
        try {
            return dataCollectionService.getPendingMe();
        }
        catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/approved-list-me")
    public List<DataCollectionRes> getApprovedMe(){
        try {
            return dataCollectionService.getApprovedMe();
        }
        catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/reject-list-me")
    public List<DataCollectionRes> getRejectMe(){
        try {
            return dataCollectionService.getRejectMe();
        }
        catch (Exception ex) {
            return null;
        }
    }

    @PostMapping ("search-for-me")
    public PageDTO<SearchDataRes> getDataForUser(@RequestBody DataSearchForUserParam dataSearchForUserParam)
            throws ParseException {

        return dataCollectionService.searchDataCollectionForUser(dataSearchForUserParam);

    }

    @PostMapping
    public MessagesResponse sendData(@RequestBody DataProvideReq dataProvideReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            dataCollectionService.sendData(dataProvideReq);
        } catch (Exception e) {
            ms.code = 500;
            ms.message = e.getMessage();
        }
        return ms;
    }

    @PutMapping
    public MessagesResponse updateData(@RequestBody UpdateDataReq updateDataReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            dataCollectionService.updateData(updateDataReq);
        } catch (Exception e) {
            ms.code = 500;
            ms.message = e.getMessage();
        }
        return ms;
    }


    // Admin
    @GetMapping("/pending-list-admin")
    public List<DataCollectionRes> getPendingAdmin(){
        try {
            return dataCollectionService.getPendingAdmin();
        }
        catch (Exception ex) {
            return null;
        }
    }

    @PostMapping ("search-for-admin")
    public PageDTO<SearchDataRes> getDataForAdmin(@RequestBody DataSearchForAdminParam dataSearchForAdminParam)
        throws ParseException {

        return dataCollectionService.searchDataCollectionForAdmin(dataSearchForAdminParam);

    }

    @PostMapping("/approve/{id}")
    public MessagesResponse approveData(@PathVariable long id) {
        MessagesResponse ms = new MessagesResponse();
        try {
            dataCollectionService.approve(id);
        } catch (Exception e) {
            ms.code = 500;
            ms.message = e.getMessage();
        }
        return ms;
    }

    @PostMapping("/reject")
    public MessagesResponse rejectData(@RequestBody DataRejectReq dataRejectReq){
        MessagesResponse ms = new MessagesResponse();
        try {
            dataCollectionService.reject(dataRejectReq);
        }
        catch (Exception exception) {
            ms.code = 500;
            ms.message = exception.getMessage();
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessagesResponse deletetData(@PathVariable long id){
        MessagesResponse ms = new MessagesResponse();
        try {
            dataCollectionService.delete(id);
        }
        catch (Exception exception) {
            ms.code = 500;
            ms.message = exception.getMessage();
        }
        return ms;
    }

}
