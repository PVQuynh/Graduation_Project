package com.example.data_collection_server.utils;

import com.example.data_collection_server.dto.response.VocabularyRes;
import com.example.data_collection_server.exception.BusinessLogicException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClassifyMedia {

    public static VocabularyRes addFieldsForVocabulary(String fileName, String url) {
        VocabularyRes vocabularyRes = new VocabularyRes();

        // Lấy fileName
        vocabularyRes.setContent(getFileName(fileName));

        // Xác định image/video
        String mediaType = classifyMedia(fileName);
        if ("IMAGE".equals(mediaType)) {
            vocabularyRes.setImageLocation(url);
        } else if ("VIDEO".equals(mediaType)) {
            vocabularyRes.setVideoLocation(url);
        }

        return vocabularyRes;
    }


    public static String classifyMedia(String fileName) {
        // Phan mo rong cua tep
        final String[] IMAGE_EXTENSIONS = { "jpg", "jpeg", "png", "gif", "webp" };
        final String[] VIDEO_EXTENSIONS = { "mp4", "avi", "mov", "wmv" };
        final String[] AUDIO_EXTENSIONS = { "mp3", "wav", "aac", "flac" };

        // Lấy phần mở rộng của tên tệp
        String extension = null;
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        }

        // Kiểm tra phần mở rộng để phân loại
        if (extension != null) {
            Set<String> videoExtensionSet = new HashSet<>(Arrays.asList(VIDEO_EXTENSIONS));
            Set<String> imageExtensionSet = new HashSet<>(Arrays.asList(IMAGE_EXTENSIONS));
            Set<String> audioExtensionSet = new HashSet<>(Arrays.asList(AUDIO_EXTENSIONS));

            if (videoExtensionSet.contains(extension.toLowerCase())) {
                return "VIDEO";
            } else if (imageExtensionSet.contains(extension.toLowerCase())) {
                return "IMAGE";
            } else if (audioExtensionSet.contains(extension.toLowerCase())) {
                return "AUDIO";
            }
        }

        // Mặc định trả về "UNKNOWN" nếu không phân loại được
        return "UNKNOWN";
    }

    public static String getFileName(String fileName) {
        // Tìm vị trí của dấu gạch dưới đầu tiên và dấu chấm cuối cùng
        int underscoreIndex = fileName.indexOf('_');
        int dotIndex = fileName.lastIndexOf('.');

        // Kiểm tra xem có dấu gạch dưới và dấu chấm trong tên file không
        if (underscoreIndex != -1 && dotIndex != -1 && underscoreIndex < dotIndex) {
            // Trích xuất phần tên giữa dấu gạch dưới và dấu chấm
           return (fileName.substring(underscoreIndex + 1, dotIndex));
        } else {
            return fileName;
        }
    }
}
