package com.example.hust_learning_server.constant.sql;

public class SQLExam {

    public static final String GET_ALL_EXAMS = """
        select
            exam.*
            from {h-schema}exam
            left join {h-schema}classRoom on classRoom.classRoom_id = exam.classRoom_id
        where
            (case
                when :classRoomId = 0 then exam.classRoom_id is null or exam.classRoom_id = exam.classRoom_id
                else exam.classRoom_id = :classRoomId
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
        	        when exam.created_by = :email then exam.name like concat('%', :nameSearch, '%')
        	        else exam.name like concat('%', :nameSearch, '%') and exam.is_private = 0
        	    end
        	    else 1
        	end)
        	order by exam.exam_id desc
        """;
}
