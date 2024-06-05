package com.example.hust_learning_server.mapper.Impl;

import com.example.hust_learning_server.dto.response.DataCollectionRes;
import com.example.hust_learning_server.entity.DataCollection;
import com.example.hust_learning_server.mapper.DataCollectionMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DataCollectionMapperImpl implements DataCollectionMapper {

  @Override
  public DataCollectionRes toDTO(DataCollection entity) {
    ModelMapper modelMapper = new ModelMapper();

    DataCollectionRes dataCollectionRes = modelMapper.map(entity, DataCollectionRes.class);

    dataCollectionRes.setVocabularyId(entity.getVocabulary().getId());
    dataCollectionRes.setVocabularyContent(entity.getVocabulary().getContent());

    dataCollectionRes.setVolunteerEmail(entity.getCreatedBy());
    dataCollectionRes.setAdminEmail(entity.getAdminEmail());

    return dataCollectionRes;

  }

  @Override
  public List<DataCollectionRes> toDTOList(List<DataCollection> entityList) {
    return entityList.stream().map(entity->toDTO(entity)).collect(Collectors.toList());
  }
}
