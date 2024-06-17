package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.constant.sql.SQLQuestion;
import com.example.hust_learning_server.entity.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select q from Question q where q.classRoomId = :classRoomId")
    List<Question>findQuestionsByClassRoomId(@Param("classRoomId") long classRoomId );

    @Query(nativeQuery = true, value = SQLQuestion.GET_ALL_QUESTIONS)
    List<Question> finAllQuestions(long classRoomId, String contentSearch);

    @Query("select q from Question q where q.classRoomId = :classRoomId")
    List<Question> findQuestionLimitsByClassRoomId(@Param("classRoomId") long classRoomId, Pageable pageable);

    @Query("select q from Question q where q.classRoomId = :classRoomId")
    List<Question>searchQuestionsByClassRoomId(@Param("classRoomId") long classRoomId, Pageable pageable);

    List<Question> findAllByClassRoomId(long id);

}
