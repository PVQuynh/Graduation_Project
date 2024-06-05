package com.example.hust_learning_server.constant.sql;

public class SQLExam {

    public static final String findAllByTopicIdAndPrivateAndCreatedByAndNameSearch = """
        select
            exam.*
            from {h-schema}exam
            join {h-schema}topic on topic.topic_id = exam.topic_id
        where
            topic.topic_id = :topicId and
        	exam.is_private = :isPrivate and
        	exam.created_by = :email and
        	exam.name like concat('%', :nameSearch, '%')
        """;

    public static final String findAllByTopicIdAndPrivateAndNameSearch = """
        select
            exam.*
            from {h-schema}exam
            join {h-schema}topic on topic.topic_id = exam.topic_id
        where
            topic.topic_id = :topicId and
        	exam.is_private = :isPrivate and
        	exam.name like concat('%', :nameSearch, '%')
        """;


}
