package com.qonsult.mapper;

import com.qonsult.dto.TopicDTO;
import com.qonsult.entity.Topic;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicMapper extends GenericMapper<Topic, TopicDTO> {
}
