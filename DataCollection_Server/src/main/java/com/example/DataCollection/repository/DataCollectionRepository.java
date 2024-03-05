package com.example.DataCollection.repository;


import com.example.DataCollection.entity.DataCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DataCollectionRepository extends JpaRepository<DataCollection, Long> {
    @Query("select data from DataCollection  data where  data.volunteerEmail= :email")
    Optional<List<DataCollection>> findByEmail(@Param("email") String email);

    @Query("select data from DataCollection  data where  data.volunteerEmail= :email and data.status=100")
    Optional<List<DataCollection>> getApproved(@Param("email") String email);

    @Query("select data from DataCollection  data where data.status=0")
    Optional<List<DataCollection>> getPending();
}
