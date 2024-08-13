package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.IntroductionReq;
import com.example.hust_learning_server.dto.response.IntroductionRes;
import com.example.hust_learning_server.entity.Introduction;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.repository.IntroductionRepository;
import com.example.hust_learning_server.service.IntroductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IntroductionServiceImpl implements IntroductionService {
    private static final long INTRODUCTION_ID = 1;
    private final IntroductionRepository introductionRepository;

    @Override
    public void addIntroduction(IntroductionReq introductionReq) {
        Introduction introduction = Introduction.builder()
                .title(introductionReq.getTitle())
                .body(introductionReq.getBody())
                .footer(introductionReq.getFooter())
                .build();
        introduction.setId(INTRODUCTION_ID);
        introductionRepository.save(introduction);
    }

    @Override
    public void updateIntroduction(long introductionId, IntroductionReq introductionReq) {
        Introduction introduction = introductionRepository.findById(INTRODUCTION_ID).orElseThrow(ResourceNotFoundException::new);
        if(introductionReq.getTitle() != null) {
            introduction.setTitle(introductionReq.getTitle());
        }
        if(introductionReq.getBody() != null) {
            introduction.setBody(introductionReq.getBody());
        }
        if(introductionReq.getFooter() != null) {
            introduction.setFooter(introductionReq.getFooter());
        }
        introductionRepository.save(introduction);
    }

    @Override
    public IntroductionRes getIntroduction(long introductionId) {
        Optional<Introduction> introductionOptional = introductionRepository.findById(INTRODUCTION_ID);
        if(introductionOptional.isPresent()) {
            return IntroductionRes.builder()
                    .introductionId(introductionId)
                    .title(introductionOptional.get().getTitle())
                    .body(introductionOptional.get().getBody())
                    .footer(introductionOptional.get().getFooter())
                    .build();
        }
        return null;
    }
}
