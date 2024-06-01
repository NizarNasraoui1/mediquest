package com.qonsult.service;

import com.qonsult.entity.Schema;
import reactor.core.publisher.Mono;

public interface SchemaService {
    Mono<Schema> addSchema(String schemaName);
}
