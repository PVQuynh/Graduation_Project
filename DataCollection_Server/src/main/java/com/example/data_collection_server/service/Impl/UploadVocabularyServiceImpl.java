package com.example.data_collection_server.service.Impl;


import com.example.data_collection_server.dto.response.VocabularyRes;
import com.example.data_collection_server.service.UploadVocabularyService;
import com.example.data_collection_server.utils.ClassifyMedia;
import com.example.data_collection_server.utils.GetPreSignedUrlUtils;
import com.example.data_collection_server.utils.MergeVocabularyRes;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Data
public class UploadVocabularyServiceImpl implements UploadVocabularyService {

    private final MinioClient minioClient;
    private final ApplicationEventPublisher publisher;
    private final GetPreSignedUrlUtils getPreSignedUrlUtils;

    String dataCollectionBucketName = "vocabularies";

    @Value("${minio.default.folder}")
    String defaultBaseFolder;

    @Override
    public VocabularyRes uploadFile(String name, byte[] content) {
        try {
            InputStream inputStream = new ByteArrayInputStream(content);

            // Tải lên tệp tin lên Minio sử dụng InputStream
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(dataCollectionBucketName)
                    .object(defaultBaseFolder + name)
                    .stream(inputStream, content.length, -1) // Sử dụng InputStream
                    .build()
            );

            // Tạo pre-signed URL cho tệp tin vừa tải lên
            String url =
                minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(dataCollectionBucketName)
                        .object(defaultBaseFolder + name)
                        .expiry(7, TimeUnit.DAYS)
                        .build());

            return ClassifyMedia.addFieldsForVocabulary(name, getPreSignedUrlUtils.getPreSignedUrl(url));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<VocabularyRes> uploadFileList(List<MultipartFile> files) throws IOException {
        List<VocabularyRes> vocabularyResList = new LinkedList<>();

        for (MultipartFile file :
                files) {
            vocabularyResList.add(uploadFile(file.getOriginalFilename(), file.getBytes()));
        }

        // Merge cac file co noi dung giong nhau
        return MergeVocabularyRes.mergeVocabularies(vocabularyResList);
    }

    @Override
    public byte[] getFile(String key) {

        try {
            InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                    .bucket(dataCollectionBucketName)
                    .object(defaultBaseFolder + "/" + key)
                    .build());
            // Read data from stream

            byte[] content = IOUtils.toByteArray(stream);
            stream.close();

            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<String> getAllFile() {

        try {
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(dataCollectionBucketName).build());

            List<String> filesName = new ArrayList<>();
            for (Result<Item> result : results) {
                Item item = result.get();
                String fileName = item.objectName();
                filesName.add(fileName);
            }

            return filesName;
        } catch (Exception ex) {
            ex.printStackTrace();
           return null;
        }
    }

    @Override
    public boolean deleteFile(String fileName) {
        try {
            boolean isObisObjectExists = minioClient.statObject(
                    StatObjectArgs.builder().bucket(dataCollectionBucketName).object(fileName).build()
            ) != null;

            if (isObisObjectExists) {
                minioClient.removeObject(
                        RemoveObjectArgs.builder().bucket(dataCollectionBucketName).object(fileName).build());
                return true;
            }

            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
