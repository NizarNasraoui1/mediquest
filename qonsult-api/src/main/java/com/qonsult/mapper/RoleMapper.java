package com.qonsult.mapper;

import com.qonsult.dto.RoleDTO;
import com.qonsult.entity.auth.Role;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<Role, RoleDTO> {
}
