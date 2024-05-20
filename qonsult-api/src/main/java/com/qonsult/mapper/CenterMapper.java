package com.qonsult.mapper;

import com.qonsult.dto.CenterDTO;
import com.qonsult.entity.Center;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CenterMapper extends GenericMapper<Center, CenterDTO> {
}
