package com.example.hust_learning_server.repository;


import com.example.hust_learning_server.entity.DataCollection;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DataCollectionRepository extends JpaRepository<DataCollection, Long> {

    // Me
    @Query("select data from DataCollection data where data.createdBy= :email order by data.createdDate desc")
    List<DataCollection> getAllMe(@Param("email") String email);

    @Query("select data from DataCollection data where data.createdBy= :email and data.status=:status order by data.createdDate desc")
    List<DataCollection> getOptionsList(@Param("email") String email, @Param("status") int status);

    @Query("select data from DataCollection data where data.createdBy= :email and data.status=100 order by data.createdDate desc")
    List<DataCollection> getPendingMe(@Param("email") String email);

    @Query("select data from DataCollection data where data.createdBy= :email and data.status=200 order by data.createdDate desc")
    List<DataCollection> getApprovedMe(@Param("email") String email);

    @Query("select data from DataCollection data where data.createdBy= :email and data.status=300 order by data.createdDate desc")
    List<DataCollection> getRejectMe(@Param("email") String email);

    // Admin
    @Query("select data from DataCollection data where data.status=:status order by data.createdDate desc")
    List<DataCollection> getOptionsListAdmin(@Param("status") int status);

    @Query("select data from DataCollection data where data.status=100 order by data.createdDate desc")
    List<DataCollection> getPendingAdmin();

    @Query("select data from DataCollection data where data.status=200 order by data.createdDate desc")
    List<DataCollection> getApprovedAdmin();

    @Query("select data from DataCollection data where data.status=300 order by data.createdDate desc")
    List<DataCollection> getRejectAdmin(@Param("email") String email);

    List<DataCollection> findAllByVocabularyId(long id);
}
