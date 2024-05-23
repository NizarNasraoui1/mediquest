package com.qonsult.mapper;

import com.qonsult.dto.QuestionnaireModelDTO;
import com.qonsult.entity.QuestionnaireModel;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionnaireModelMapper extends GenericMapper<QuestionnaireModel, QuestionnaireModelDTO> {

}
