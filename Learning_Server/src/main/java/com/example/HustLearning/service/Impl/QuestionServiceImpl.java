package com.example.HustLearning.service.Impl;

import com.example.HustLearning.dto.request.QuestionReq;
import com.example.HustLearning.dto.request.QuestionLimitReq;
import com.example.HustLearning.dto.request.UpdateQuestionReq;
import com.example.HustLearning.dto.response.QuestionRes;
import com.example.HustLearning.entity.Question;
import com.example.HustLearning.exception.BusinessLogicException;
import com.example.HustLearning.mapper.QuestionMapper;
import com.example.HustLearning.repository.QuestionRepository;
import com.example.HustLearning.service.QuestionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

        List<Question> questions = questionRepository.findQuestionsByTopicId(topicId).orElse(null);

        return questionMapper.toDTOList(questions);
    }

    @Override
    public List<QuestionRes> questionLimits(QuestionLimitReq questionLimitReq) {
        Pageable pageable = PageRequest.of(questionLimitReq.getPage()-1, questionLimitReq.getSize());

        List<Question> questions = questionRepository.findQuestionLimitsByTopicId(questionLimitReq.getTopicId(), pageable).orElse(null);

        return questionMapper.toDTOList(questions);
    }

    @Override
    public void addQuestion(QuestionReq questionReq) {
        Question question = questionRepository.findQuestionsByContent(questionReq.getContent());

        if (ObjectUtils.isEmpty(question)) {
            question = questionMapper.toEntity(questionReq);
            questionRepository.save(question);
        }
    }

    @Override
    public void updateQuestion(UpdateQuestionReq updateQuestionReq) {
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
        questionRepository.deleteById(id);
    }



}
