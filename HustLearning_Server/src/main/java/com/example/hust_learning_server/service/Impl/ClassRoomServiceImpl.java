package com.example.hust_learning_server.service.Impl;


import com.example.hust_learning_server.dto.request.ClassRoomReq;
import com.example.hust_learning_server.dto.request.UpdateClassRoomReq;
import com.example.hust_learning_server.dto.response.ClassRoomRes;
import com.example.hust_learning_server.entity.ClassRoom;
import com.example.hust_learning_server.entity.Question;
import com.example.hust_learning_server.entity.Topic;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.exception.UnAuthorizedException;
import com.example.hust_learning_server.repository.ClassRoomRepository;
import com.example.hust_learning_server.repository.TopicRepository;
import com.example.hust_learning_server.service.ClassRoomService;
import com.example.hust_learning_server.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {
    private final ClassRoomRepository classRoomRepository;
    private final TopicRepository topicRepository;


    @Override
    public List<ClassRoomRes> getAllClassRoom() {
        List<ClassRoom> classRooms = classRoomRepository.findAll();
        if (classRooms.isEmpty()) return Collections.emptyList();
        List<ClassRoomRes> classRoomResList = classRooms.stream()
                .map(c -> {
                    ClassRoomRes res = new ClassRoomRes();
                    BeanUtils.copyProperties(c, res);
                    res.setClassRoomId(c.getId());
                    return res;
                })
                .toList();
        return classRoomResList;
    }

    @Override
    public void addClassRoom(ClassRoomReq classRoomReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) throw new UnAuthorizedException();
        ClassRoom classRoom = new ClassRoom();
        BeanUtils.copyProperties(classRoomReq,classRoom);
        classRoomRepository.save(classRoom);
    }

    @Override
    public void updateClassRoom(UpdateClassRoomReq updateClassRoomReq) {
        ClassRoom classRoom = classRoomRepository.findById(updateClassRoomReq.getClassRoomId()).orElseThrow(ResourceNotFoundException::new);
        if (updateClassRoomReq.getContent() != null) {
            classRoom.setContent(updateClassRoomReq.getContent());
        }
        if (updateClassRoomReq.getImageLocation() !=null) {
            classRoom.setImageLocation(updateClassRoomReq.getImageLocation());
        }
        classRoomRepository.save(classRoom);
    }

    @Override
    public void deleteClassRoomById(long id) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }

        List<Topic> topics = topicRepository.findAllByClassRoomId(id);
        if (!topics.isEmpty()) {
            for (Topic topic : topics) topic.setClassRoom(null);
            topicRepository.saveAll(topics);
        }

        classRoomRepository.deleteById(id);
    }
}
