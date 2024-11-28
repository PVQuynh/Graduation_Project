package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.LessonReq;
import com.example.hust_learning_server.dto.response.LessonRes;
import com.example.hust_learning_server.entity.ClassRoom;
import com.example.hust_learning_server.entity.Lesson;
import com.example.hust_learning_server.entity.Part;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.repository.ClassRoomRepository;
import com.example.hust_learning_server.repository.LessonRepository;
import com.example.hust_learning_server.repository.PartRepository;
import com.example.hust_learning_server.service.LessonService;
import com.example.hust_learning_server.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final ClassRoomRepository classRoomRepository;
    private final PartRepository partRepository;
    private final PartService partService;

    @Override
    public void addLesson(LessonReq lessonReq) {
        ClassRoom classRoom = classRoomRepository.findById(lessonReq.getClassRoomId()).orElseThrow(ResourceNotFoundException::new);

        Lesson lesson = Lesson.builder()
                .lessonName(lessonReq.getLessonName())
                .imageLocation(lessonReq.getImageLocation())
                .videoLocation(lessonReq.getVideoLocation())
                .classRoomId(classRoom.getId())
                .build();
        lessonRepository.save(lesson);
    }

    @Override
    public LessonRes getById(long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(ResourceNotFoundException::new);
        ClassRoom classRoom = classRoomRepository.findById(lesson.getClassRoomId()).orElse(null);
        return LessonRes.builder()
                .lessonId(lesson.getId())
                .lessonName(lesson.getLessonName())
                .imageLocation(lesson.getImageLocation())
                .videoLocation(lesson.getVideoLocation())
                .classRoomId(lesson.getClassRoomId())
                .classRoomContent(classRoom.getContent())
                .build();
    }

    @Override
    public List<LessonRes> getAll(long classRoomId) {
        List<LessonRes> lessonResList = new ArrayList<>();
        if (classRoomId != 0) {
            ClassRoom classRoom = classRoomRepository.findById(classRoomId).orElseThrow(ResourceNotFoundException::new);
            List<Lesson> lessons = lessonRepository.findAllByClassRoomId(classRoomId);
            for (Lesson lesson : lessons) {
                LessonRes lessonRes = LessonRes.builder()
                        .lessonId(lesson.getId())
                        .lessonName(lesson.getLessonName())
                        .imageLocation(lesson.getImageLocation())
                        .videoLocation(lesson.getVideoLocation())
                        .classRoomId(lesson.getClassRoomId())
                        .classRoomContent(classRoom.getContent())
                        .build();
                lessonResList.add(lessonRes);
            }
        } else {
            List<Lesson> lessons = lessonRepository.findAll();
            for (Lesson lesson : lessons) {
                ClassRoom classRoom = classRoomRepository.findById(lesson.getClassRoomId()).orElse(null);
                LessonRes lessonRes = LessonRes.builder()
                        .lessonId(lesson.getId())
                        .lessonName(lesson.getLessonName())
                        .imageLocation(lesson.getImageLocation())
                        .videoLocation(lesson.getVideoLocation())
                        .classRoomId(lesson.getClassRoomId())
                        .classRoomContent(classRoom!= null ? classRoom.getContent() : null)
                        .build();
                lessonResList.add(lessonRes);
            }
        }
        return lessonResList;
    }

    @Override
    public void updateLesson(LessonRes lessonRes) {
        Lesson lesson = lessonRepository.findById(lessonRes.getLessonId()).orElseThrow(ResourceNotFoundException::new);

        if (lessonRes.getLessonName() != null) {
            lesson.setLessonName(lessonRes.getLessonName().trim());
        }
        if (lessonRes.getImageLocation() != null) {
            lesson.setImageLocation(lessonRes.getImageLocation().trim());
        }
        if (lessonRes.getVideoLocation() != null) {
            lesson.setVideoLocation(lessonRes.getVideoLocation().trim());
        }
        lessonRepository.save(lesson);
    }

    @Override
    public void deleteById(long lessonId) {
        List<Part> parts = partRepository.findAllByLessonId(lessonId);
        parts.forEach(part -> {
            partService.deletePart(part.getId());
        });
        lessonRepository.deleteById(lessonId);
    }
}