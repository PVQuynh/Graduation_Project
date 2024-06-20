package com.example.hust_learning_server.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import com.example.hust_learning_server.dto.request.ExamReq;
import com.example.hust_learning_server.dto.request.ExamSavedReq;
import com.example.hust_learning_server.dto.request.ExamScoringReq;
import com.example.hust_learning_server.dto.response.ExamRes;
import com.example.hust_learning_server.dto.response.ExamResForUser;
import com.example.hust_learning_server.dto.response.ExamSavedRes;
import com.example.hust_learning_server.entity.Exam;
import com.example.hust_learning_server.entity.Question;
import com.example.hust_learning_server.entity.QuestionExamMapping;
import com.example.hust_learning_server.entity.QuestionExamUserMapping;
import com.example.hust_learning_server.entity.User;
import com.example.hust_learning_server.entity.UserExamMapping;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.exception.UnAuthorizedException;
import com.example.hust_learning_server.repository.ExamRepository;
import com.example.hust_learning_server.repository.QuestionExamMappingRepository;
import com.example.hust_learning_server.repository.QuestionExamUserMappingRepository;
import com.example.hust_learning_server.repository.QuestionRepository;
import com.example.hust_learning_server.repository.ClassRoomRepository;
import com.example.hust_learning_server.repository.UserExamMappingRepository;
import com.example.hust_learning_server.repository.UserRepository;
import com.example.hust_learning_server.service.ExamService;
import com.example.hust_learning_server.utils.CommonUtils;
import com.example.hust_learning_server.utils.EmailUtils;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final QuestionExamMappingRepository questionExamMappingRepository;
    private final UserExamMappingRepository userExamMappingRepository;
    private final QuestionExamUserMappingRepository questionExamUserMappingRepository;


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
        // save exam
        Exam exam = examRepository.save(Exam.builder()
            .name(examReq.getName())
            .isPrivate(examReq.isPrivate())
            .classRoomId(examReq.getClassRoomId())
            .build());
        // save question exam mapping
        List<QuestionExamMapping> questionExamMappings = examReq.getQuestionIds().stream()
            .filter(questionId -> !questionExamMappingRepository.existsByQuestionIdAndExamId(questionId, exam.getId()))
            .map(questionId -> {
                QuestionExamMapping questionExamMapping = QuestionExamMapping.builder()
                    .questionId(questionId)
                    .examId(exam.getId())
                    .build();
                return questionExamMapping;
            })
            .toList();
        questionExamMappingRepository.saveAll(questionExamMappings);
    }

    @Override
    public void addExamsForUser(List<Long> examIds, long userId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
        List<Exam> exams = examRepository.findAllById(examIds);
        if (exams.size() != examIds.size()) {
            throw new ResourceNotFoundException();
        }
        List<UserExamMapping> userExamMappings = examIds.stream()
            .filter(examId -> !userExamMappingRepository.existsByUserIdAndExamId(user.getId(), examId))
            .map(examId -> {
                 UserExamMapping userExamMapping = UserExamMapping.builder()
                    .userId(userId)
                    .examId(examId)
                    .isFinish(false)
                    .build();
                 return userExamMapping;
            })
            .toList();
        userExamMappingRepository.saveAll(userExamMappings);
    }

    @Override
    public void examScoring(ExamScoringReq examScoringReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        User user = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        UserExamMapping userExamMapping = userExamMappingRepository.findByUserIdAndExamId(user.getId(), examScoringReq.getExamId())
            .orElseThrow(ResourceNotFoundException::new);
        userExamMapping.setScore(examScoringReq.getScore());
        userExamMapping.setFinish(true);
        userExamMappingRepository.save(userExamMapping);
    }

    @Override
    public void examSaved(List<ExamSavedReq> examSavedReqs) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        User user = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        List<QuestionExamUserMapping> questionExamUserMappings = examSavedReqs.stream()
            .filter(examSavedReq -> !questionExamUserMappingRepository.existsByQuestionIdAndExamIdAndUserId(examSavedReq.getQuestionId(),
                examSavedReq.getExamId(), user.getId()))
            .map(examSavedReq -> {
                QuestionExamUserMapping questionExamUserMapping = new QuestionExamUserMapping();
                BeanUtils.copyProperties(examSavedReq, questionExamUserMapping);
                questionExamUserMapping.setUserId(user.getId());
                return questionExamUserMapping;
            })
            .toList();
        questionExamUserMappingRepository.saveAll(questionExamUserMappings);
        List<QuestionExamUserMapping> existingQuestionExamUserMappings = examSavedReqs.stream()
            .filter(examSavedReq -> questionExamUserMappingRepository.existsByQuestionIdAndExamIdAndUserId(examSavedReq.getQuestionId(), examSavedReq.getExamId(), user.getId()))
            .map(examSavedReq -> {
                QuestionExamUserMapping questionExamUserMapping = questionExamUserMappingRepository.findByQuestionIdAndExamIdAndUserId(examSavedReq.getQuestionId(), examSavedReq.getExamId(), user.getId()).orElseThrow(ResourceNotFoundException::new);
                BeanUtils.copyProperties(examSavedReq, questionExamUserMapping);
                questionExamUserMapping.setUserId(user.getId());
                return questionExamUserMapping;
            })
            .toList();
        questionExamUserMappingRepository.saveAll(existingQuestionExamUserMappings);
    }

    @Override
    public List<ExamSavedRes> getExamSaved(long examId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        User user = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        List<QuestionExamUserMapping> questionExamUserMappings = questionExamUserMappingRepository.findByExamIdAndUserId(examId, user.getId());
        List<ExamSavedRes> examSavedRes = questionExamUserMappings.stream()
            .map(questionExamUserMapping -> {
                ExamSavedRes savedRes = new ExamSavedRes();
                BeanUtils.copyProperties(questionExamUserMapping, savedRes);
                return savedRes;
            }).toList();
        return examSavedRes;
    }

    @Override
    public ExamRes getExamById(long id) {
        Exam exam = examRepository.findById(id).orElse(null);
        if (exam == null) {
            return null;
        }
        ExamRes examRes = new ExamRes();
        BeanUtils.copyProperties(exam, examRes);
        List<QuestionExamMapping> questionExamMappings = questionExamMappingRepository.findAllByExamId(exam.getId());
        examRes.setExamId(exam.getId());
        examRes.setClassRoomId(exam.getClassRoomId());
        examRes.setNumberOfQuestions(questionExamMappings.size());
        return examRes;
    }

    @Override
    public Page<ExamRes> getAllExams(long classRoomId, String isPrivate, String nameSearch, Pageable pageable) {
        String email = EmailUtils.getCurrentUser();
        // check private
        int checkPrivate = CommonUtils.convertPrivate(isPrivate);
        Page<Exam> exams = examRepository.findAllExam(classRoomId, checkPrivate, email, nameSearch, pageable);
        if (exams.isEmpty()) {
            return null;
        }
        List<ExamRes> examResList = new ArrayList<>();
        for (Exam exam : exams) {
            ExamRes examRes = new ExamRes();
            BeanUtils.copyProperties(exam, examRes);
            List<QuestionExamMapping> questionExamMappings = questionExamMappingRepository.findAllByExamId(exam.getId());
            examRes.setExamId(exam.getId());
            examRes.setClassRoomId(exam.getClassRoomId());
            examRes.setNumberOfQuestions(questionExamMappings.size());
            examResList.add(examRes);
        }
        return new PageImpl<>(examResList, pageable, exams.getTotalElements());
    }

    @Override
    public Page<ExamResForUser> getAllExamsForUser(Pageable pageable) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        User user = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        Page<UserExamMapping> userExamMappings = userExamMappingRepository.findAllByUserId(user.getId(), pageable);
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
                    examResForUser.setClassRoomId(exam.getClassRoomId());
                    examResForUsers.add(examResForUser);
                }
            }
        }
        return new PageImpl<>(examResForUsers, pageable, userExamMappings.getTotalElements());
    }

    @Transactional
    @Override
    public void deleteExamOfUser(long examId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        User user = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);

        List<QuestionExamUserMapping> questionExamUserMappings = questionExamUserMappingRepository.findByExamIdAndUserId(examId, user.getId());
        questionExamUserMappingRepository.deleteAll(questionExamUserMappings);
        UserExamMapping userExamMapping = userExamMappingRepository.findByUserIdAndExamId(user.getId(), examId)
            .orElseThrow(ResourceNotFoundException::new);
        userExamMappingRepository.delete(userExamMapping);
    }

    @Transactional
    @Override
    public void deleteExam(long examId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        User user = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        Exam exam = examRepository.findById(examId).orElseThrow(ResourceNotFoundException::new);

        List<QuestionExamUserMapping> questionExamUserMappings = questionExamUserMappingRepository.findByExamIdAndUserId(examId, user.getId());
        questionExamUserMappingRepository.deleteAll(questionExamUserMappings);
        List<QuestionExamMapping> questionExamMappings = questionExamMappingRepository.findAllByExamId(examId);
        questionExamMappingRepository.deleteAll(questionExamMappings);
        UserExamMapping userExamMapping = userExamMappingRepository.findByUserIdAndExamId(user.getId(), examId).orElse(null);
        if (Objects.nonNull(userExamMapping)) {
            userExamMappingRepository.delete(userExamMapping);
        }
        examRepository.delete(exam);
    }
}
