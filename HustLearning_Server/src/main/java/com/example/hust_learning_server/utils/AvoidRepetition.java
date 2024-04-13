package com.example.hust_learning_server.utils;

import com.example.hust_learning_server.entity.Vocabulary;
import com.example.hust_learning_server.entity.VocabularyImage;
import com.example.hust_learning_server.entity.VocabularyVideo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvoidRepetition {
    public static List<Vocabulary> avoidRepeatingVocabularyContent(List<Vocabulary> vocabularyList) {
        Map<String, Vocabulary> map = new HashMap<>();

        for (Vocabulary vocabulary: vocabularyList) {
            String content = vocabulary.getContent();

            if (!map.containsKey(content)) {
                map.put(content, vocabulary);
            }
        }

        return new ArrayList<>(map.values());
    }

    public static List<VocabularyImage> avoidRepeatingVocabularyImageLocation(List<VocabularyImage> vocabularyImages) {
        Map<String, VocabularyImage> map = new HashMap<>();

        for (VocabularyImage image: vocabularyImages) {
            String location = image.getImageLocation();

            if (!map.containsKey(location)) {
                map.put(location, image);
            }
        }

        return new ArrayList<>(map.values());
    }

    public static List<VocabularyVideo> avoidRepeatingVocabularyVideoLocation(List<VocabularyVideo> vocabularyVideos) {
        Map<String, VocabularyVideo> map = new HashMap<>();

        for (VocabularyVideo video: vocabularyVideos) {
            String location = video.getVideoLocation();

            if (!map.containsKey(location)) {
                map.put(location, video);
            }
        }

        return new ArrayList<>(map.values());
    }
}
