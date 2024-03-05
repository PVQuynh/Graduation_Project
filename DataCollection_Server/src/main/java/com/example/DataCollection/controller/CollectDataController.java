package com.example.DataCollection.controller;

import com.example.DataCollection.dto.request.DataProvideReq;
import com.example.DataCollection.dto.request.DataRejectReq;
import com.example.DataCollection.dto.response.MessagesResponse;
import com.example.DataCollection.service.DataCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-approved")
    public MessagesResponse getApproved(){
        MessagesResponse ms = new MessagesResponse();
        try {
            ms.data = dataCollectionService.getApproved();
        }
        catch (Exception ex) {
            ms.code = 5000;
            ms.message = ex.getMessage();
        }
        return  ms;
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
