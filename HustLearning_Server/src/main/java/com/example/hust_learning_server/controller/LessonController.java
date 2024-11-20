package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.request.LessonReq;
import com.example.hust_learning_server.dto.response.LessonRes;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("lessons")
public class LessonController {
    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity<MessageResponse> addLesson(@RequestBody LessonReq lessonReq) {
        MessageResponse ms = new MessageResponse();
        lessonService.addLesson(lessonReq);
        return ResponseEntity.ok(ms);

    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<MessageResponse> getLessonById(
            @PathVariable("lessonId") long lessonId
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = lessonService.getById(lessonId);
        return ResponseEntity.ok(ms);

    }

    @GetMapping("/all")
    public ResponseEntity<MessageResponse> getAllLessons(
            @RequestParam(required = false, defaultValue = "0") long classRoomId
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = lessonService.getAll(classRoomId);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateLesson(@RequestBody LessonRes lessonRes) {
        MessageResponse ms = new MessageResponse();
        lessonService.updateLesson(lessonRes);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<MessageResponse> deleteLessonById(@PathVariable("lessonId") long lessonId) {
        MessageResponse ms = new MessageResponse();
        lessonService.deleteById(lessonId);
        return ResponseEntity.ok(ms);
    }
}
