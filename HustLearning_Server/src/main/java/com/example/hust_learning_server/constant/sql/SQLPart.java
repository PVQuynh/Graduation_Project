package com.example.hust_learning_server.constant.sql;

public class SQLPart {

    public static final String GET_ALL_PARTS = """
            select part.*
            from part
            join lesson on lesson.lesson_id = part.lesson_id
            join class_room on class_room.class_room_id = lesson.class_room_id
            where
                    class_room.class_room_id =
                        (case
                            when :classRoomId = 0 then class_room.class_room_id
                            else :classRoomId
                        end)
                and
                    lesson.lesson_id =
                        (case
                            when :lessonId = 0 then lesson.lesson_id
                            else :lessonId
                        end)
                and
                    part.part_name like concat('%', :searchContent, '%')
            order by
            	substring_index(part_name, ' ', 1),
                substring_index(substring_index(part_name, ' ', 2), ' ', -1),
                substring_index(substring_index(part_name, ' ', 3), ' ', -1),
                substring_index(substring_index(part_name, ' ', 4), ' ', -1),
                substring_index(substring_index(part_name, ' ', 5), ' ', -1),
                substring_index(substring_index(part_name, ' ', 6), ' ', -1),
                substring_index(substring_index(part_name, ' ', 7), ' ', -1),
                substring_index(substring_index(part_name, ' ', 8), ' ', -1),
                substring_index(substring_index(part_name, ' ', 9), ' ', -1),
                substring_index(substring_index(part_name, ' ', 10), ' ', -1),
                substring_index(part_name, ' ', -1);
            """;
}
