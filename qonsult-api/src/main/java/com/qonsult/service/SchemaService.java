package com.qonsult.service;

import com.qonsult.entity.auth.Schema;
import reactor.core.publisher.Mono;

public interface SchemaService {
    Mono<Schema> addSchema(String schemaName);
}
