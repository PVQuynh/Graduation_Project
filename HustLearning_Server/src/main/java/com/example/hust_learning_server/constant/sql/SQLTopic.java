package com.example.hust_learning_server.constant.sql;

public class SQLTopic {


    public static final String GET_ALL_TOPICS = """
        select
            topic.*
            from {h-schema}topic
            left join {h-schema}class_room on class_room.class_room_id = topic.class_room_id
        where
            (case
                when :classRoomId = 0 then topic.class_room_id is null or topic.class_room_id = topic.class_room_id
                else topic.class_room_id = :classRoomId
            end)
            and
            topic.is_private =
                 (case
                    when :isPrivate = 0 then 0
                    when :isPrivate = 1 then 1
                    when :isPrivate = -1 then topic.is_private
                 end)
            and
            (case
                when :isPrivate = 1 then topic.created_by = :email
                else 1
            end)
            and
            (case
                when :contentSearch is not null then
                    case
                        when topic.created_by = :email then topic.content like concat('%', :contentSearch, '%') and topic.created_by = :email
                        else topic.content like concat('%', :contentSearch, '%') and topic.is_private = 0
                    end
                else 1
            end)
            order by topic.topic_id desc
        """;
}
