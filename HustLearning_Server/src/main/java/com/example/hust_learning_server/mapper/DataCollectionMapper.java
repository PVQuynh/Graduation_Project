package com.example.hust_learning_server.mapper;

import com.example.hust_learning_server.dto.response.DataCollectionRes;
import com.example.hust_learning_server.entity.DataCollection;
import java.util.List;

public interface DataCollectionMapper {

  DataCollectionRes toDTO(DataCollection entity);

  List<DataCollectionRes> toDTOList(List<DataCollection> entityList);

;

}
