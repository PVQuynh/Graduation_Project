package com.example.HustLearning.mapper.Impl;

import com.example.HustLearning.dto.response.DataCollectionRes;
import com.example.HustLearning.dto.response.SearchDataRes;
import com.example.HustLearning.entity.DataCollection;
import com.example.HustLearning.mapper.DataCollectionMapper;
import com.example.HustLearning.mapper.SearchDataMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SearchDataMapperImpl implements SearchDataMapper {

  @Override
  public SearchDataRes toDTO(DataCollection entity) {
    ModelMapper modelMapper = new ModelMapper();

    SearchDataRes searchDataRes = modelMapper.map(entity, SearchDataRes.class);
    searchDataRes.setVocab(entity.getVocab().getContent());
    searchDataRes.setTopic(entity.getVocab().getTopic().getContent());
    searchDataRes.setStatus(entity.getStatus());
    return searchDataRes;

  }

  @Override
  public List<SearchDataRes> toDTOList(List<DataCollection> entityList) {
    return entityList.stream().map(entity->toDTO(entity)).collect(Collectors.toList());
  }
}
