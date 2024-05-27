package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.QuestionReq;
import com.example.hust_learning_server.dto.request.QuestionLimitReq;
import com.example.hust_learning_server.dto.request.UpdateQuestionReq;
import com.example.hust_learning_server.dto.response.QuestionRes;
import com.example.hust_learning_server.entity.Answer;
import com.example.hust_learning_server.entity.Question;
import com.example.hust_learning_server.entity.Topic;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.exception.UnAuthorizedException;
import com.example.hust_learning_server.mapper.QuestionMapper;
import com.example.hust_learning_server.repository.AnswerRepository;
import com.example.hust_learning_server.repository.QuestionRepository;
import com.example.hust_learning_server.repository.TopicRepository;
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
    private final AnswerRepository answerRepository;
    private final TopicRepository topicRepository;
    private final QuestionMapper questionMapper;

    @Override
    public List<QuestionRes> getQuestionsByTopicId(long topicId) {
        List<Question> questions = questionRepository.findQuestionsByTopicId(topicId).orElseThrow(ResourceNotFoundException::new);
        return questionMapper.toDTOList(questions);
    }

    @Override
    public List<QuestionRes> questionLimits(QuestionLimitReq questionLimitReq) {
        Pageable pageable = PageRequest.of(questionLimitReq.getPage() - 1, questionLimitReq.getSize());
        List<Question> questions = questionRepository.findQuestionLimitsByTopicId(questionLimitReq.getTopicId(), pageable).orElseThrow(ResourceNotFoundException::new);
        return questionMapper.toDTOList(questions);
    }

    @Override
    public List<QuestionRes> questionLimits_v2(int page, int size, long topicId) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<Question> questions = questionRepository.findQuestionLimitsByTopicId(topicId, pageable).orElseThrow(ResourceNotFoundException::new);
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
    public void updateQuestion(UpdateQuestionReq updateQuestionReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        Question question = questionRepository.findById(updateQuestionReq.getQuestionId()).orElseThrow(ResourceNotFoundException::new);
        if (question.getContent() != null) {
            question.setContent(updateQuestionReq.getContent());
        }
        if (question.getExplanation() != null) {
            question.setExplanation(updateQuestionReq.getExplanation());
        }
        if (question.getImageLocation() != null) {
            question.setImageLocation(updateQuestionReq.getImageLocation());
        }
        if (question.getVideoLocation() != null) {
            question.setVideoLocation(updateQuestionReq.getVideoLocation());
        }
        questionRepository.save(question);
    }

    @Override
    public void deleteQuestionById(long id) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        List<Answer> answerList = answerRepository.findAllByQuestionId(id);
        if (!answerList.isEmpty())  answerRepository.deleteAll(answerList);
        questionRepository.deleteById(id);
    }


}
