package com.qonsult.mapper;

import com.qonsult.dto.CodeLabelDTO;
import com.qonsult.entity.CodeLabel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Mapper
public interface CodeLabelMapper {
    @Mapping(source = "dto.id", target = "id", qualifiedBy = StringToLong.class)
    CodeLabel tobo(CodeLabelDTO dto);

    @Mapping(source = "entity.id", target = "id", qualifiedBy = LongToString.class)
    CodeLabelDTO toDto(CodeLabel entity);

    @Mapping(source = "dto.id", target = "id", qualifiedBy = StringToLong.class)
    List<CodeLabel> toBos(List<CodeLabelDTO> dtos);

    @Mapping(source = "dto.id", target = "id", qualifiedBy = StringToLong.class)
    List<CodeLabelDTO> toDtos(List<CodeLabel> bos);
    @StringToLong
    default Long stringToLong(String id) {
        return id != null ? Long.parseLong(id) : null;
    }

    @LongToString
    default String longToString(Long id) {
        return id != null ? id.toString() : null;
    }

}
