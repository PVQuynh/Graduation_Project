package com.example.learning_server.mapper;

import com.example.learning_server.dto.response.DataCollectionRes;
import com.example.learning_server.entity.DataCollection;
import java.util.List;

public interface DataCollectionMapper {

  DataCollectionRes toDTO(DataCollection entity);

  List<DataCollectionRes> toDTOList(List<DataCollection> entityList);

;

}
