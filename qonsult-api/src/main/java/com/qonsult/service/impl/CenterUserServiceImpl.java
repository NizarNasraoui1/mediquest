package com.qonsult.service.impl;

import com.qonsult.dto.CenterUserDTO;
import com.qonsult.entity.CenterUser;
import com.qonsult.generic.GenericServiceImpl;
import com.qonsult.mapper.CenterUserMapper;
import com.qonsult.repository.CenterUserRepository;
import com.qonsult.service.CenterUserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CenterUserServiceImpl extends GenericServiceImpl<CenterUser, CenterUserDTO,Long, CenterUserRepository, CenterUserMapper> implements CenterUserService {
    public CenterUserServiceImpl(CenterUserRepository repository, CenterUserMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Mono<CenterUser> findCenterUserByUsername(String username) {
        return Mono.justOrEmpty(repository.findByUsername(username));
    }
}
