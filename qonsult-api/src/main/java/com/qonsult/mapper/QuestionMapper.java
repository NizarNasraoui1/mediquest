package com.qonsult.mapper;

import com.qonsult.dto.QuestionDTO;
import com.qonsult.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Mapping(source = "dto.id", target = "id", qualifiedBy = StringToLong.class)
    Question tobo(QuestionDTO dto);

    @Mapping(source = "entity.id", target = "id", qualifiedBy = LongToString.class)
    QuestionDTO toDto(Question entity);

    @Mapping(source = "dto.id", target = "id", qualifiedBy = StringToLong.class)
    List<Question> toBos(List<QuestionDTO> dtos);

    @Mapping(source = "dto.id", target = "id", qualifiedBy = StringToLong.class)
    List<QuestionDTO> toDtos(List<Question> bos);

    @StringToLong
    default Long stringToLong(String id) {
        return id != null ? Long.parseLong(id) : null;
    }

    @LongToString
    default String longToString(Long id) {
        return id != null ? id.toString() : null;
    }
}
