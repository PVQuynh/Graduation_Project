package com.example.hust_learning_server.service.Impl;


import com.example.hust_learning_server.service.MinioFileService;
import com.example.hust_learning_server.utils.GetPreSignedUrlUtils;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Data
public class MinioFileServiceImpl implements MinioFileService {

    private static final Logger log = LoggerFactory.getLogger(MinioFileServiceImpl.class);
    private final MinioClient minioClient;
    private final GetPreSignedUrlUtils getPreSignedUrlUtils;
    @Value("${minio.data_collection-bucket}")
    String dataCollectionBucketName;

    @Override
    public String uploadFile(String fileName, byte[] content) {
        try {
            InputStream inputStream = new ByteArrayInputStream(content);
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(dataCollectionBucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(dataCollectionBucketName)
                        .build());
            }
            // Tải lên tệp tin lên Minio sử dụng InputStream
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(dataCollectionBucketName)
                    .object(fileName)
                    .stream(inputStream, content.length, -1) // Sử dụng InputStream
                    .build()
            );

            // Tạo pre-signed URL cho tệp tin vừa tải lên
            String url =
                minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(dataCollectionBucketName)
                        .object(fileName)
                        .expiry(7, TimeUnit.DAYS)
                        .build());

            return getPreSignedUrlUtils.getPreSignedUrl(url);
        } catch (Exception e) {
            log.error("Error occurred while uploading file", e);
            return null;
        }
    }

    @Override
    public byte[] getFile(String fileName) {

        try {
            InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                    .bucket(dataCollectionBucketName)
                    .object(fileName)
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
