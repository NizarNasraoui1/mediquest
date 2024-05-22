package com.qonsult.mapper;

import com.qonsult.dto.QuestionnaireResponseDTO;
import com.qonsult.entity.QuestionnaireResponse;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionnaireAnswerMapper extends GenericMapper<QuestionnaireResponse, QuestionnaireResponseDTO> {
}
