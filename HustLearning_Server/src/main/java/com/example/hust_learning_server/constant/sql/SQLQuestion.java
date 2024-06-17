package com.example.hust_learning_server.constant.sql;

public class SQLQuestion {

    public static final String GET_ALL_QUESTIONS = """
        select
            question.*
            from {h-schema}question
            left join {h-schema}class_room on class_room.class_room_id = question.class_room_id
        where
           (case
                when :classRoomId = 0 then question.class_room_id is null or question.class_room_id = question.class_room_id
                else question.class_room_id = :classRoomId
           end)
           and
           (case
                when :contentSearch is not null then question.content like concat('%', :contentSearch, '%')
                else 1
           end)
           order by question.question_id desc
        """;

}
