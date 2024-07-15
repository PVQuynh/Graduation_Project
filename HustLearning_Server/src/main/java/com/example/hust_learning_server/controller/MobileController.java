package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.MobileReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobiles")
public class MobileController {

    @Autowired
    private MobileService mobileService;;

    @PostMapping
    public ResponseEntity<MessageResponse> addLinkMobile(@RequestBody MobileReq mobileReq) {
        MessageResponse ms = new MessageResponse();
        mobileService.addLinkMobile(mobileReq);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/all")
    public ResponseEntity<MessageResponse> getAllLinkMobile() {
        MessageResponse ms = new MessageResponse();
        ms.data = mobileService.getAllLinkMobile();
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/{mobileId}")
    public ResponseEntity<MessageResponse> getAllLinkMobile(@PathVariable("mobileId") long mobileId) {
        MessageResponse ms = new MessageResponse();
        ms.data = mobileService.getLinkMobile(mobileId);
        return ResponseEntity.ok(ms);
    }

    @PutMapping("/{mobileId}")
    public ResponseEntity<MessageResponse> updateLinkMobile(
            @PathVariable("mobileId") long mobileId,
            @RequestBody MobileReq mobileReq) {
        MessageResponse ms = new MessageResponse();
        mobileService.updateLinkMobile(mobileId, mobileReq);
        return ResponseEntity.ok(ms);

    }

    @DeleteMapping("/{mobileId}")
    public ResponseEntity<MessageResponse> deleteLinkMobile(@PathVariable("mobileId") long mobileId) {
        MessageResponse ms = new MessageResponse();
        mobileService.deleteLinkMobile(mobileId);
        return ResponseEntity.ok(ms);
    }


}
