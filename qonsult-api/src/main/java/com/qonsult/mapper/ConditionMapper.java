package com.qonsult.mapper;

import com.qonsult.dto.ConditionDTO;
import com.qonsult.entity.Condition;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConditionMapper extends GenericMapper<Condition, ConditionDTO> {
}
