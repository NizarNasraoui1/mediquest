package com.qonsult.mapper;

import com.qonsult.dto.GroupDTO;
import com.qonsult.entity.auth.Group;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper extends GenericMapper<Group, GroupDTO> {
}
