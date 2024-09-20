package com.example.hust_learning_server.utils;

import com.example.hust_learning_server.dto.response.VocabularyCollectionRes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeVocabularyRes {

    public static List<VocabularyCollectionRes> mergeVocabularies(List<VocabularyCollectionRes> vocabularyResList) {
        // Tạo một HashMap để lưu trữ các đối tượng đã được hợp nhất theo nội dung
        Map<String, VocabularyCollectionRes> mergedMap = new HashMap<>();

        // Duyệt qua mảng đối tượng và hợp nhất chúng vào HashMap
        for (VocabularyCollectionRes vocabularyRes : vocabularyResList) {
            String content = vocabularyRes.getContent();
            // Nếu HashMap đã chứa nội dung của đối tượng
            if (mergedMap.containsKey(content)) {
                VocabularyCollectionRes existingVocabularyCollectionRes = mergedMap.get(content);
                if (vocabularyRes.getImageLocation() != null) {
                    existingVocabularyCollectionRes.setImageLocation(vocabularyRes.getImageLocation());
                }
                if (vocabularyRes.getVideoLocation() != null) {
                    existingVocabularyCollectionRes.setVideoLocation(vocabularyRes.getVideoLocation());
                }
            } else {
                // Nếu HashMap chưa chứa nội dung của đối tượng, thêm nó vào HashMap
                mergedMap.put(content, vocabularyRes);
            }
        }

        // Chuyển đổi HashMap thành danh sách và trả về
        return new ArrayList<>(mergedMap.values());
    }

}
