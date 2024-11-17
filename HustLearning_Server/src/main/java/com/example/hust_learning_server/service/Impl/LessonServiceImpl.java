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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final ClassRoomRepository classRoomRepository;
    private final PartRepository partRepository;

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
    public LessonRes getById(Long lessonId) {
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
    public List<LessonRes> getAll(Long classRoomId) {
        ClassRoom classRoom = classRoomRepository.findById(classRoomId).orElseThrow(ResourceNotFoundException::new);
        List<Lesson> lessons = lessonRepository.findAllByClassRoomId(classRoomId);
        List<LessonRes> lessonResList = new ArrayList<>();
        for (Lesson lesson : lessons) {
            LessonRes lessonRes = LessonRes.builder()
                    .lessonId(lesson.getId())
                    .lessonName(lesson.getLessonName())
                    .imageLocation(lesson.getImageLocation())
                    .videoLocation(lesson.getVideoLocation())
                    .classRoomId(lesson.getId())
                    .build();
            lessonRes.setClassRoomId(classRoom.getId());
            lessonResList.add(lessonRes);
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
    public void deleteById(Long lessonId) {
        List<Part> parts = partRepository.findAllByLessonId(lessonId);
        parts.forEach(part -> {
            part.setLessonId(null);
        });
        partRepository.saveAll(parts);
        lessonRepository.deleteById(lessonId);
    }
}