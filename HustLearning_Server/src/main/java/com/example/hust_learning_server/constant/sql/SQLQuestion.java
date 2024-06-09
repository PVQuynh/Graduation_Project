package com.example.hust_learning_server.constant.sql;

public class SQLQuestion {

    public static final String GET_ALL_QUESTIONS = """
        select
            question.*
            from {h-schema}question
            join {h-schema}topic on topic.topic_id = question.topic_id
        where
            topic.topic_id =
                (case
                    when :topicId = 0 then topic.topic_id
                    else :topicId
                end)
            and
            (case
                when :contentSearch is not null then question.content like concat('%', :contentSearch, '%')
                else 1
            end)
            order by question.question_id desc  
        """;
}
