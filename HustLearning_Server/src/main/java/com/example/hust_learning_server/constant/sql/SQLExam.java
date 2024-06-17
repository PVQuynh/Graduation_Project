package com.example.hust_learning_server.constant.sql;

public class SQLExam {

    public static final String GET_ALL_EXAMS = """
        select
            exam.*
            from {h-schema}exam
            left join {h-schema}class_room on class_room.class_room_id = exam.class_room_id
        where
            (case
                when :classRoomId = 0 then exam.class_room_id is null or exam.class_room_id = exam.class_room_id
                else exam.class_room_id = :classRoomId
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
