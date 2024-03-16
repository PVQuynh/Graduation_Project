package com.example.hust_learning_server.mapper;

import com.example.hust_learning_server.dto.response.SearchDataRes;
import com.example.hust_learning_server.entity.DataCollection;
import java.util.List;

public interface SearchDataMapper {

  SearchDataRes toDTO(DataCollection entity);

  List<SearchDataRes> toDTOList(List<DataCollection> entityList);

;

}
