package com.example.HustLearning.service.Impl;

import com.example.HustLearning.entity.Answer;
import com.example.HustLearning.exception.BusinessLogicException;
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

}
