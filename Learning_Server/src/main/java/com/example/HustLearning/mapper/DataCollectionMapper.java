package com.example.HustLearning.mapper;

import com.example.HustLearning.dto.response.DataCollectionRes;
import com.example.HustLearning.entity.DataCollection;
import java.util.List;

public interface DataCollectionMapper {



  DataCollectionRes toDTO(DataCollection entity);

  List<DataCollectionRes> toDTOList(List<DataCollection> entityList);

;

}
