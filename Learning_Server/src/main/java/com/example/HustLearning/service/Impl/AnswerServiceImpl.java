package com.example.HustLearning.service.Impl;

import com.example.HustLearning.entity.Answer;
import com.example.HustLearning.repository.AnswerRepository;
import com.example.HustLearning.repository.QuestionRepository;
import com.example.HustLearning.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public List<Answer> getAllAnswer() {
        return answerRepository.findAll();
    }

    @Override
    public Answer getAnswerById(long id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);

        if (optionalAnswer.isPresent()) {
            return optionalAnswer.get();
        }

        return null;
    }

    @Override
    public void addAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public Answer deleteAnswerById(long id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);

        if (answerRepository.findById(id).isPresent()) {
            answerRepository.deleteById(id);
            return optionalAnswer.get();
        }

        return null;
    }

    @Override
    public List<Answer> getAnswersByQuestionId(long questionId) {
        if (questionRepository.findById(questionId).isPresent()) {
            return answerRepository.findAnswersByQuestionId(questionId);
        }

        return null;
    }
}
