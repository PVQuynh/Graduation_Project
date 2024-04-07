package com.example.data_collection_server.service.Impl;


import com.example.data_collection_server.service.UploadMediaGeneralService;
import com.example.data_collection_server.utils.GetPreSignedUrlUtils;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Data
public class UploadMediaGeneralServiceImpl implements UploadMediaGeneralService {

    private final MinioClient minioClient;
    private final ApplicationEventPublisher publisher;
    private final GetPreSignedUrlUtils getPreSignedUrlUtils;

    String mediaGeneralBucketName = "media-general";

    @Value("${minio.default.folder}")
    String defaultBaseFolder;

    @Override
    public String uploadFile(String name, byte[] byteContent) {
        try {
            InputStream inputStream = new ByteArrayInputStream(byteContent);

            // Tải lên tệp tin lên Minio sử dụng InputStream
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(mediaGeneralBucketName)
                    .object(defaultBaseFolder + name)
                    .stream(inputStream, byteContent.length, -1) // Sử dụng InputStream
                    .build()
            );

            // Tạo pre-signed URL cho tệp tin vừa tải lên
            String url =
                minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(mediaGeneralBucketName)
                        .object(defaultBaseFolder + name)
                        .expiry(7, TimeUnit.DAYS)
                        .build());

            return getPreSignedUrlUtils.getPreSignedUrl(url);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public byte[] getFile(String key) {

        try {
            InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                    .bucket(mediaGeneralBucketName)
                    .object(defaultBaseFolder + "/" + key)
                    .build());
            // Read data from stream

            byte[] byteContent = IOUtils.toByteArray(stream);
            stream.close();

            return byteContent;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<String> getAllFile() {

        try {
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(mediaGeneralBucketName).build());

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
                    StatObjectArgs.builder().bucket(mediaGeneralBucketName).object(fileName).build()
            ) != null;

            if (isObisObjectExists) {
                minioClient.removeObject(
                        RemoveObjectArgs.builder().bucket(mediaGeneralBucketName).object(fileName).build());
                return true;
            }

            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
