package com.example.hust_learning_server.constant.sql;

public class SQLQuestion {

    public static final String GET_ALL_QUESTIONS = """
        select
            question.*
            from {h-schema}question
            left join {h-schema}classRoom on classRoom.classRoom_id = question.classRoom_id
        where
           (case
                when :classRoomId = 0 then question.classRoom_id is null or question.classRoom_id = question.classRoom_id
                else question.classRoom_id = :classRoomId
           end)
           and
           (case
                when :contentSearch is not null then question.content like concat('%', :contentSearch, '%')
                else 1
           end)
           order by question.question_id desc
        """;
}
