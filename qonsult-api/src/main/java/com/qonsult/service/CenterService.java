package com.qonsult.service;

import com.qonsult.dto.CenterDTO;
import com.qonsult.entity.Center;
import com.qonsult.generic.GenericService;
import com.qonsult.mapper.CenterMapper;
import com.qonsult.repository.CenterRepository;
import reactor.core.publisher.Mono;

public interface CenterService extends GenericService<Center, CenterDTO,Long, CenterRepository, CenterMapper> {
    public Mono<Center> addCenter(CenterDTO centerDTO,String schemaName);
}
