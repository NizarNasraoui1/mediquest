package com.qonsult.mapper;

import com.qonsult.dto.QuestionnaireDTO;
import com.qonsult.entity.Questionnaire;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionnaireMapper extends GenericMapper<Questionnaire, QuestionnaireDTO> {

}
