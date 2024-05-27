package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.ClassRoomReq;
import com.example.hust_learning_server.dto.request.UpdateClassRoomReq;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.ClassRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("classrooms")
public class ClassRoomController {
    private final ClassRoomService classRoomService;

    @GetMapping("/all")
    public ResponseEntity<MessageResponse> getAllClassrooms() {
        MessageResponse ms = new MessageResponse();
        ms.data = classRoomService.getAllClassRoom();
        return ResponseEntity.ok(ms);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> saveClassroom(@RequestBody ClassRoomReq classRoomReq) {
        MessageResponse ms = new MessageResponse();
        classRoomService.addClassRoom(classRoomReq);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateClassroom(@RequestBody UpdateClassRoomReq updateClassRoomReq) {
        MessageResponse ms = new MessageResponse();
        classRoomService.updateClassRoom(updateClassRoomReq);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponse> deleteClassroom(@PathVariable("id") long id) {
        MessageResponse ms = new MessageResponse();
        classRoomService.deleteClassRoomById(id);
        return ResponseEntity.ok(ms);
    }
}
