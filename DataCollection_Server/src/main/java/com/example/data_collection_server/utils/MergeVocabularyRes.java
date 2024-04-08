package com.example.data_collection_server.utils;

import com.example.data_collection_server.dto.response.VocabularyRes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeVocabularyRes {

    public static List<VocabularyRes> mergeVocabularies(List<VocabularyRes> vocabularyResList) {
        // Tạo một HashMap để lưu trữ các đối tượng đã được hợp nhất theo nội dung
        Map<String, VocabularyRes> mergedMap = new HashMap<>();

        // Duyệt qua mảng đối tượng và hợp nhất chúng vào HashMap
        for (VocabularyRes vocabularyRes : vocabularyResList) {
            String content = vocabularyRes.getContent();
            // Nếu HashMap đã chứa nội dung của đối tượng
            if (mergedMap.containsKey(content)) {
                VocabularyRes existingVocabularyRes = mergedMap.get(content);
                if (vocabularyRes.getImageLocation() != null) {
                    existingVocabularyRes.setImageLocation(vocabularyRes.getImageLocation());
                }
                if (vocabularyRes.getVideoLocation() != null) {
                    existingVocabularyRes.setVideoLocation(vocabularyRes.getVideoLocation());
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
