package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.QuestionReq;
import com.example.hust_learning_server.dto.request.QuestionLimitReq;
import com.example.hust_learning_server.dto.request.UpdateQuestionReq;
import com.example.hust_learning_server.dto.response.QuestionRes;
import com.example.hust_learning_server.entity.Question;
import com.example.hust_learning_server.exception.BusinessLogicException;
import com.example.hust_learning_server.mapper.QuestionMapper;
import com.example.hust_learning_server.repository.QuestionRepository;
import com.example.hust_learning_server.service.QuestionService;
import com.example.hust_learning_server.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper;

    @Override
    public List<QuestionRes> getQuestionsByTopicId(long topicId) {
        List<Question> questions = questionRepository.findQuestionsByTopicId(topicId).orElseThrow(BusinessLogicException::new);
        if (questions.isEmpty()) throw new BusinessLogicException();

        return questionMapper.toDTOList(questions);
    }

    @Override
    public List<QuestionRes> questionLimits(QuestionLimitReq questionLimitReq) {
        Pageable pageable = PageRequest.of(questionLimitReq.getPage()-1, questionLimitReq.getSize());

        List<Question> questions = questionRepository.findQuestionLimitsByTopicId(questionLimitReq.getTopicId(), pageable).orElseThrow(BusinessLogicException::new);
        if (questions.isEmpty()) throw new BusinessLogicException();

        return questionMapper.toDTOList(questions);
    }

    @Override
    public void addQuestion(QuestionReq questionReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        Question question = questionMapper.toEntity(questionReq);
        questionRepository.save(question);
    }

    @Override
    public void updateQuestion(UpdateQuestionReq updateQuestionReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        Question question = questionRepository.findById(updateQuestionReq.getQuestionId()).orElseThrow(BusinessLogicException::new);

        if (question.getContent()!=null) {
            question.setContent(updateQuestionReq.getContent());
        }
        if (question.getExplanation()!=null) {
            question.setExplanation(updateQuestionReq.getExplanation());
        }
        if (question.getImageLocation()!=null) {
            question.setImageLocation(updateQuestionReq.getImageLocation());
        }
        if (question.getVideoLocation()!=null) {
            question.setVideoLocation(updateQuestionReq.getVideoLocation());
        }

        questionRepository.save(question);
    }

    @Override
    public void deleteQuestionById(long id) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        questionRepository.deleteById(id);
    }



}
