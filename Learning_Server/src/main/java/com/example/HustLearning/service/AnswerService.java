package com.example.HustLearning.service;

import com.example.HustLearning.entity.Answer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AnswerService {

    List<Answer> getAllAnswer();

    Answer getAnswerById(long id);

    void addAnswer(Answer answer);

    Answer deleteAnswerById(long id);

    List<Answer> getAnswersByQuestionId(long questionId);
}
