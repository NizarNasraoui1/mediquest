package com.qonsult.service;

import com.qonsult.dto.CenterUserDTO;
import com.qonsult.entity.auth.CenterUser;
import com.qonsult.generic.GenericService;
import com.qonsult.mapper.CenterUserMapper;
import com.qonsult.repository.CenterUserRepository;
import reactor.core.publisher.Mono;

public interface CenterUserService extends GenericService<CenterUser, CenterUserDTO,Long, CenterUserRepository, CenterUserMapper> {
    Mono<CenterUser> findCenterUserByUsername(String username);
}
