package com.example.HustLearning.controller;

import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.DataProvideReq;
import com.example.HustLearning.dto.request.DataRejectReq;
import com.example.HustLearning.dto.request.DataSearchparam;
import com.example.HustLearning.dto.response.MessagesResponse;
import com.example.HustLearning.dto.response.SearchDataRes;
import com.example.HustLearning.service.DataCollectionService;
import java.text.ParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collect-data")
@RequiredArgsConstructor
public class CollectDataController {
    private final DataCollectionService dataCollectionService;

    @GetMapping("/get-history")
    public MessagesResponse getHistory(){
        MessagesResponse ms = new MessagesResponse();
        try {
            ms.data = dataCollectionService.getHistory();
        }
        catch (Exception ex) {
            ms.code = 5000;
            ms.message = ex.getMessage();
        }
        return  ms;
    }

    @PostMapping ("/get-approved")
    public PageDTO<SearchDataRes> getApproved(@RequestBody DataSearchparam dataSearchparam)
        throws ParseException {

           return dataCollectionService.getApproved(dataSearchparam);

    }

    @PostMapping ("get-data")
    public PageDTO<SearchDataRes> getData(@RequestBody DataSearchparam dataSearchparam)
        throws ParseException {

        return dataCollectionService.searchDataCollection(dataSearchparam);

    }

    @GetMapping("/get-pending")
    public MessagesResponse getPending(){
        MessagesResponse ms = new MessagesResponse();
        try {
            ms.data = dataCollectionService.getPending();
        }
        catch (Exception ex) {
            ms.code = 5000;
            ms.message = ex.getMessage();
        }
        return  ms;
    }

    @PostMapping("/send-data")
    public MessagesResponse sendData(@RequestBody DataProvideReq dataProvideReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            dataCollectionService.sendData(dataProvideReq);
        } catch (Exception e) {
            ms.code = 5000;
            ms.message = e.getMessage();
        }
        return ms;
    }

    @GetMapping("/approve/{dataCollectionId}")
    public MessagesResponse approveData(@PathVariable long dataCollectionId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            dataCollectionService.approve(dataCollectionId);
        } catch (Exception e) {
            ms.code = 5000;
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
            ms.code = 5000;
            ms.message = exception.getMessage();
        }
        return ms;
    }

}
