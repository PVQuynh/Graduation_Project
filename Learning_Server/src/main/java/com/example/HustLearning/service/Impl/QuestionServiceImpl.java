package com.example.HustLearning.service.Impl;

import com.example.HustLearning.dto.request.QuestionReq;
import com.example.HustLearning.dto.request.QuestionSearchParam;
import com.example.HustLearning.dto.response.QuestionRes;
import com.example.HustLearning.entity.Question;
import com.example.HustLearning.mapper.QuestionMapper;
import com.example.HustLearning.repository.QuestionRepository;
import com.example.HustLearning.service.QuestionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    @PersistenceContext
    private final EntityManager entityManager;

    private final QuestionMapper questionMapper;


    @Override
    public void addQuestion(QuestionReq questionReq) {
        Question question = questionMapper.toEntity(questionReq);
        questionRepository.save(question);
    }


    @Override
    public void deleteQuestionById(long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<QuestionRes> getQuestionsByTopicId(long topicId) {
        List<Question> questions = questionRepository.findQuestionsByTopicId(topicId).orElse(null);
        return questionMapper.toDTOList(questions);
    }

    @Override
    public List<QuestionRes> searchQuestion(QuestionSearchParam searchParam) {
        Pageable pageable = PageRequest.of(searchParam.page, searchParam.size);

        List<Question> questions = questionRepository.findQuestionsByTopicId(searchParam.topicId).orElse(null);
        return questionMapper.toDTOList(questions);
    }
}
