package com.qonsult.generic;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

import java.util.List;

@MapperConfig
public interface GenericMapper<B, D> {

    List<D> toDtos(List<B> dtos);

    List<B> toBos(List<D> bos);

    D toDto(B bo);

    B toBo(D dto);

    //    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdDateTime", ignore = true)
//    @Mapping(target = "updatedDateTime", ignore = true)
    B fillBo(D dto, @MappingTarget B bo);
}