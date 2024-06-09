package com.example.hust_learning_server.constant.sql;

public class SQLExam {

    public static final String GET_ALL_EXAMS = """
        select
            exam.*
            from {h-schema}exam
            join {h-schema}topic on topic.topic_id = exam.topic_id
        where
            topic.topic_id =
                (case
                    when :topicId = 0 then topic.topic_id
                    else :topicId
                end)
            and
        	exam.is_private =
                 (case
                    when :isPrivate = 0 then 0
                    when :isPrivate = 1 then 1
                    when :isPrivate = -1 then exam.is_private
                 end)
        	and
        	(case
                when :isPrivate = 1 then exam.created_by = :email
                else 1
            end)
        	and
        	(case
        	    when :nameSearch is not null then
        	    case
        	        when exam.created_by = :email then exam.name like concat('%', :nameSearch, '%') and exam.created_by = :email
        	        else exam.name like concat('%', :nameSearch, '%') and exam.is_private = 0
        	    end
        	    else 1
        	end)
        """;
}
