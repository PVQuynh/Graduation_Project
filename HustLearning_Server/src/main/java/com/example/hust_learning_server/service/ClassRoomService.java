package com.example.hust_learning_server.service;


import com.example.hust_learning_server.dto.request.ClassRoomReq;
import com.example.hust_learning_server.dto.request.UpdateClassRoomReq;
import com.example.hust_learning_server.dto.response.ClassRoomRes;

import java.util.List;

public interface ClassRoomService {

    List<ClassRoomRes> getAllClassRoom();

    void addClassRoom(ClassRoomReq classRoomReq);

    void updateClassRoom(UpdateClassRoomReq updateClassRoomReq);

    void deleteClassRoomById(long id);

}
