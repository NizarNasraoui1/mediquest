package com.qonsult.mapper;

import com.qonsult.dto.QuestionAnswerDTO;
import com.qonsult.entity.QuestionAnswer;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionAnswerMapper extends GenericMapper<QuestionAnswer, QuestionAnswerDTO> {
}
