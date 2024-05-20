package com.qonsult.mapper;

import com.qonsult.dto.UserDTO;
import com.qonsult.entity.User;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserDTO> {
}
