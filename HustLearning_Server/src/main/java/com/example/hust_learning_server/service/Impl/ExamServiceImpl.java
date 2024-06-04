package com.example.hust_learning_server.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import com.example.hust_learning_server.dto.request.ExamReq;
import com.example.hust_learning_server.dto.response.ExamRes;
import com.example.hust_learning_server.dto.response.ExamResForUser;
import com.example.hust_learning_server.entity.Exam;
import com.example.hust_learning_server.entity.Question;
import com.example.hust_learning_server.entity.QuestionExamMapping;
import com.example.hust_learning_server.entity.Topic;
import com.example.hust_learning_server.entity.User;
import com.example.hust_learning_server.entity.UserExamMapping;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.exception.UnAuthorizedException;
import com.example.hust_learning_server.repository.ExamRepository;
import com.example.hust_learning_server.repository.QuestionExamMappingRepository;
import com.example.hust_learning_server.repository.QuestionRepository;
import com.example.hust_learning_server.repository.TopicRepository;
import com.example.hust_learning_server.repository.UserExamMappingRepository;
import com.example.hust_learning_server.repository.UserRepository;
import com.example.hust_learning_server.service.ExamService;
import com.example.hust_learning_server.utils.EmailUtils;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExamServiceImpl implements ExamService {

    private ExamRepository examRepository;
    private QuestionRepository questionRepository;
    private TopicRepository topicRepository;
    private UserRepository userRepository;
    private QuestionExamMappingRepository questionExamMappingRepository;
    private UserExamMappingRepository userExamMappingRepository;

    @Transactional
    @Override
    public void addExam(ExamReq examReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        List<Question> questions = questionRepository.findAllById(examReq.getQuestionIds());
        if (questions.size() != examReq.getQuestionIds().size()) {
            throw new ResourceNotFoundException();
        }
        Topic topic = topicRepository.findById(examReq.getTopicId()).orElseThrow(ResourceNotFoundException::new);
        // save exam
        Exam exam = examRepository.save(Exam.builder()
            .name(examReq.getName())
            .isPrivate(examReq.isPrivate())
            .topic(topic)
            .build());
        // save question exam mapping
        List<QuestionExamMapping> questionExamMappings = new ArrayList<>();
        for (Long questionId : examReq.getQuestionIds()) {
            QuestionExamMapping questionExamMapping = QuestionExamMapping.builder()
                .questionId(questionId)
                .examId(exam.getId())
                .build();
            questionExamMappings.add(questionExamMapping);
        }
        questionExamMappingRepository.saveAll(questionExamMappings);
    }

    @Override
    public void addExamsForUser(List<Long> examIds) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        User user = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        List<Exam> exams = examRepository.findAllById(examIds);
        if (exams.size() != examIds.size()) {
            throw new ResourceNotFoundException();
        }
        List<UserExamMapping> userExamMappings = new ArrayList<>();
        for (Long examId : examIds) {
            UserExamMapping userExamMapping = UserExamMapping.builder()
                .userId(user.getId())
                .examId(examId)
                .isFinish(false)
                .build();
            userExamMappings.add(userExamMapping);
        }
        userExamMappingRepository.saveAll(userExamMappings);
    }

    @Override
    public List<ExamRes> getAllExams(long topicId, boolean isPrivate) {
        List<Exam> exams = new ArrayList<>();
        if (isPrivate) {
            String email = EmailUtils.getCurrentUser();
            if (ObjectUtils.isEmpty(email)) {
                throw new UnAuthorizedException();
            }
            exams = examRepository.findAllByTopicIdAndPrivateAndAuthor(topicId, isPrivate, email);
        } else {
            exams = examRepository.findAllByTopicIdAndPrivate(topicId, isPrivate);
        }
        if (exams.isEmpty()) {
            return null;
        }

        List<ExamRes> examResList = new ArrayList<>();
        for (Exam exam : exams) {
            ExamRes examRes = new ExamRes();
            BeanUtils.copyProperties(exam, examRes);
            List<QuestionExamMapping> questionExamMappings = questionExamMappingRepository.findAllByExamId(exam.getId());
            examRes.setExamId(exam.getId());
            examRes.setTopicId(exam.getTopic().getId());
            examRes.setNumberOfQuestions(questionExamMappings.size());
            examResList.add(examRes);
        }
        return examResList;
    }

    @Override
    public List<ExamResForUser> getAllExamsForUser() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        User user = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        List<UserExamMapping> userExamMappings = userExamMappingRepository.findAllByUserId(user.getId());
        List<ExamResForUser> examResForUsers = new ArrayList<>();
        if (!userExamMappings.isEmpty()) {
            for (UserExamMapping userExamMapping : userExamMappings) {
                ExamResForUser examResForUser = new ExamResForUser();
                Exam exam = examRepository.findById(userExamMapping.getExamId()).orElse(null);
                if (Objects.nonNull(exam)) {
                    examResForUser.setExamId(exam.getId());
                    examResForUser.setName(exam.getName());
                    List<QuestionExamMapping> questionExamMappings = questionExamMappingRepository.findAllByExamId(exam.getId());
                    examResForUser.setNumberOfQuestions(questionExamMappings.size());
                    examResForUser.setScore(userExamMapping.getScore());
                    examResForUser.setFinish(userExamMapping.isFinish());
                    examResForUser.setTopicId(exam.getTopic().getId());
                    examResForUsers.add(examResForUser);
                }
            }
        }
        return examResForUsers;
    }
}
