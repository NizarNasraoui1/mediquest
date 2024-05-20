package com.qonsult.mapper;

import com.qonsult.dto.QuestionnaireAnswerDTO;
import com.qonsult.entity.QuestionnaireAnswer;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionnaireAnswerMapper extends GenericMapper<QuestionnaireAnswer, QuestionnaireAnswerDTO> {
}
