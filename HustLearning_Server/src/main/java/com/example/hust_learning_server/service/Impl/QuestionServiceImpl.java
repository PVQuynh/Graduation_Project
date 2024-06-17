package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.DeleteQuestionsReq;
import com.example.hust_learning_server.dto.request.QuestionReq;
import com.example.hust_learning_server.dto.request.QuestionLimitReq;
import com.example.hust_learning_server.dto.request.UpdateAnswerReq;
import com.example.hust_learning_server.dto.request.UpdateQuestionReq;
import com.example.hust_learning_server.dto.response.QuestionRes;
import com.example.hust_learning_server.entity.Answer;
import com.example.hust_learning_server.entity.BaseEntity;
import com.example.hust_learning_server.entity.Question;
import com.example.hust_learning_server.entity.QuestionExamMapping;
import com.example.hust_learning_server.entity.QuestionExamUserMapping;
import com.example.hust_learning_server.entity.Topic;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.exception.UnAuthorizedException;
import com.example.hust_learning_server.mapper.QuestionMapper;
import com.example.hust_learning_server.repository.AnswerRepository;
import com.example.hust_learning_server.repository.QuestionExamMappingRepository;
import com.example.hust_learning_server.repository.QuestionExamUserMappingRepository;
import com.example.hust_learning_server.repository.QuestionRepository;
import com.example.hust_learning_server.repository.TopicRepository;
import com.example.hust_learning_server.service.QuestionService;
import com.example.hust_learning_server.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final TopicRepository topicRepository;
    private final QuestionMapper questionMapper;
    private final QuestionExamMappingRepository questionExamMappingRepository;
    private final QuestionExamUserMappingRepository questionExamUserMappingRepository;

    @Override
    public QuestionRes getQuestionById(long id) {
        Question question = questionRepository.findById(id).orElse(null);
        if (question == null) {
            return null;
        }
        return questionMapper.toDTO(question);
    }

    @Override
    public List<QuestionRes> getAllQuestions(long topicId, String contentSearch) {
        if (Strings.isBlank(contentSearch)) {
            contentSearch = null;
        }
        List<Question> questions = questionRepository.finAllQuestions(topicId, contentSearch);
        if (questions.isEmpty()) {
            return null;
        }
        return questionMapper.toDTOList(questions);
    }

    @Override
    public List<QuestionRes> getQuestionsByTopicId(long topicId) {
        List<Question> questions = questionRepository.findQuestionsByTopicId(topicId);
        if (questions.isEmpty()) {
            return null;
        }
        return questionMapper.toDTOList(questions);
    }

    @Override
    public List<QuestionRes> getQuestionsByExamId(long examId) {
        List<QuestionExamMapping> questionExamMappings = questionExamMappingRepository.findAllByExamId(examId);
        if (questionExamMappings.isEmpty()) {
            return null;
        }
        List<Question> questions = new ArrayList<>();
        for (QuestionExamMapping questionExamMapping : questionExamMappings) {
            Question question = questionRepository.findById(questionExamMapping.getQuestionId()).orElse(null);
            if (Objects.isNull(question)) {
                continue;
            }
            questions.add(question);
        }
        return questionMapper.toDTOList(questions);
    }

    @Override
    public List<QuestionRes> questionLimits(QuestionLimitReq questionLimitReq) {
        Pageable pageable = PageRequest.of(questionLimitReq.getPage() - 1, questionLimitReq.getSize());
        List<Question> questions = questionRepository.findQuestionLimitsByTopicId(questionLimitReq.getTopicId(), pageable);
        if (questions.isEmpty()) {
            return null;
        }
        return questionMapper.toDTOList(questions);
    }

    @Override
    public List<QuestionRes> questionLimits_v2(int page, int size, long topicId) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<Question> questions = questionRepository.findQuestionLimitsByTopicId(topicId, pageable);
        if (questions.isEmpty()) {
            return null;
        }
        return questionMapper.toDTOList(questions);
    }

    @Override
    public void addQuestion(QuestionReq questionReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        // check có topic dung ko
        Topic topic = topicRepository.findById(questionReq.getTopicId()).orElseThrow(ResourceNotFoundException::new);

        Question question = questionMapper.toEntity(questionReq);
        questionRepository.save(question);
    }

    @Override
    public void addListQuestions(List<QuestionReq> questionReqList) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        for (QuestionReq questionReq : questionReqList) {
            // check có topic dung ko
            Topic topic = topicRepository.findById(questionReq.getTopicId()).orElseThrow(ResourceNotFoundException::new);
            Question question = questionMapper.toEntity(questionReq);
            questionRepository.save(question);
        }
    }

    @Transactional
    @Override
    public void updateQuestion(UpdateQuestionReq updateQuestionReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        Question question = questionRepository.findById(updateQuestionReq.getQuestionId()).orElseThrow(ResourceNotFoundException::new);
        if (updateQuestionReq.getContent() != null) {
            question.setContent(updateQuestionReq.getContent());
        }
        if (updateQuestionReq.getExplanation() != null) {
            question.setExplanation(updateQuestionReq.getExplanation());
        }
        if (updateQuestionReq.getImageLocation() != null) {
            question.setImageLocation(updateQuestionReq.getImageLocation());
        }
        if (updateQuestionReq.getVideoLocation() != null) {
            question.setVideoLocation(updateQuestionReq.getVideoLocation());
        }
        if (updateQuestionReq.getQuestionType() != null) {
            question.setQuestionType(updateQuestionReq.getQuestionType());
        }
        if (updateQuestionReq.getFileType() != null) {
            question.setFileType(updateQuestionReq.getFileType());
        }
        if (updateQuestionReq.getVideoLocation() != null) {
            question.setVideoLocation(updateQuestionReq.getVideoLocation());
        }
        questionRepository.save(question);
        if (!ObjectUtils.isEmpty(updateQuestionReq.getUpdateAnswerReqs())) {
            for (UpdateAnswerReq updateAnswerReq : updateQuestionReq.getUpdateAnswerReqs()) {
                Answer answer = answerRepository.findById(updateAnswerReq.getAnswerId()).orElseThrow(ResourceNotFoundException::new);
                if (updateAnswerReq.getContent() != null) {
                    answer.setContent(updateAnswerReq.getContent());
                }
                if (updateAnswerReq.getImageLocation() != null) {
                    answer.setImageLocation(updateAnswerReq.getImageLocation());
                }
                if (updateAnswerReq.getVideoLocation() != null) {
                    answer.setVideoLocation(updateAnswerReq.getVideoLocation());
                }
                answer.setCorrect(updateAnswerReq.isCorrect());
                answerRepository.save(answer);
            }
            // Xóa các answer ko truyền vào
            List<Answer> answers = answerRepository.findAllByQuestionId(updateQuestionReq.getQuestionId());
            HashSet<Long> allAnswerIds = new HashSet<>(answers.stream().map(BaseEntity::getId).toList());
            HashSet<Long> updateAnswerReqIds = new HashSet<>(updateQuestionReq.getUpdateAnswerReqs().stream().map(a->a.getAnswerId()).toList());
            for (long updateAnswerReqId : updateAnswerReqIds) {
                if (!allAnswerIds.contains(updateAnswerReqId)) {
                    answerRepository.deleteById(updateAnswerReqId);
                }
            }
        }
    }

    @Override
    public void deleteQuestionById(long id) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        List<Answer> answerList = answerRepository.findAllByQuestionId(id);
        if (!answerList.isEmpty()) {
            answerRepository.deleteAll(answerList);
        }
        List<QuestionExamMapping> questionExamMappings = questionExamMappingRepository.findAllByQuestionId(id);
        questionExamMappingRepository.deleteAll(questionExamMappings);
        List<QuestionExamUserMapping> questionExamUserMappings = questionExamUserMappingRepository.findAllByQuestionId(id);
        questionExamUserMappingRepository.deleteAll(questionExamUserMappings);
        questionRepository.deleteById(id);
    }

    @Override
    public void deleteQuestions(DeleteQuestionsReq deleteQuestionsReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        for (Long id : deleteQuestionsReq.getQuestionIds()) {
            List<Answer> answerList = answerRepository.findAllByQuestionId(id);
            if (!answerList.isEmpty()) {
                answerRepository.deleteAll(answerList);
            }
            List<QuestionExamMapping> questionExamMappings = questionExamMappingRepository.findAllByQuestionId(id);
            questionExamMappingRepository.deleteAll(questionExamMappings);
            List<QuestionExamUserMapping> questionExamUserMappings = questionExamUserMappingRepository.findAllByQuestionId(id);
            questionExamUserMappingRepository.deleteAll(questionExamUserMappings);
            questionRepository.deleteById(id);
        }
    }
}
