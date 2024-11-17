package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.LessonReq;
import com.example.hust_learning_server.dto.response.LessonRes;

import java.util.List;

public interface LessonService {

    void addLesson(LessonReq lessonReq);

    LessonRes getById(Long lessonId);
    
    List<LessonRes> getAll(Long classRoomId);

    void updateLesson(LessonRes lessonRes);

    void deleteById(Long lessonId);
}
