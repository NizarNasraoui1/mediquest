package com.qonsult.mapper;

import com.qonsult.dto.CenterUserDTO;
import com.qonsult.entity.auth.CenterUser;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CenterUserMapper extends GenericMapper<CenterUser, CenterUserDTO> {
}
