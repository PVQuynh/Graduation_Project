package com.example.HustLearning.mapper;

import com.example.HustLearning.dto.response.SearchDataRes;
import com.example.HustLearning.entity.DataCollection;
import java.util.List;

public interface SearchDataMapper {

  SearchDataRes toDTO(DataCollection entity);

  List<SearchDataRes> toDTOList(List<DataCollection> entityList);

;

}
