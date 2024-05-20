package com.qonsult.mapper;

import com.qonsult.dto.QuestionnaireRequestDTO;
import com.qonsult.entity.QuestionnaireRequest;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionnaireRequestMapper extends GenericMapper<QuestionnaireRequest, QuestionnaireRequestDTO> {
}
