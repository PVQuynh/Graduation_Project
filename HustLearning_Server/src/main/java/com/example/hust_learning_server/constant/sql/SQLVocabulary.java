package com.example.hust_learning_server.constant.sql;

public class SQLVocabulary {

    public static final String GET_ALL_VOCABULARIES = """
        select
            vocabulary.*
            from {h-schema}vocabulary
            left join {h-schema}topic on topic.topic_id = vocabulary.topic_id
        where
            (case
                when :topicId = 0 then vocabulary.topic_id is null or vocabulary.topic_id = vocabulary.topic_id
                else vocabulary.topic_id = :topicId
            end)
            and
            vocabulary.vocabulary_type =
                (case
                    when :vocabularyType is not null then :vocabularyType
                    else vocabulary.vocabulary_type
                end)
            and
            (case
                when :isPrivate = -1 then topic.is_private is null or topic.is_private = topic.is_private
                else  topic.is_private = :isPrivate
            end)
            and
            (case
                when :isPrivate = 1 then vocabulary.created_by = :email
                else 1
            end)
            and
            (case
                when :contentSearch is not null then
                    case
                        when vocabulary.created_by = :email then vocabulary.content like concat('%', :contentSearch, '%')
                        else vocabulary.content like concat('%', :contentSearch, '%') and (topic.is_private = 0 or topic.is_private is null)
                    end
                else 1
            end)
            order by vocabulary.vocabulary_id desc
        """;
}
