package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.Topic;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findByContent(String content);

    @Query("select topic from Topic topic where topic.classRoom.id = :classRoomId order by topic.id desc")
    List<Topic> findAllTopicByClassRoomId(@Param("classRoomId") long classRoomId);

    @Query("select topic from Topic topic where topic.classRoom.id = :classRoomId and topic.isPrivate = false order by topic.id desc")
    List<Topic> findAllCommonTopicByClassRoomId(@Param("classRoomId") long classRoomId);

    @Query("select topic from Topic topic where topic.classRoom.id = :classRoomId and topic.createdBy = :email and topic.isPrivate = true order by topic.id desc")
    List<Topic> findAllPrivateTopicByClassRoomId(@Param("classRoomId") long classRoomId, @Param("email") String email);

    List<Topic> findAllByClassRoomId(long id);
}
