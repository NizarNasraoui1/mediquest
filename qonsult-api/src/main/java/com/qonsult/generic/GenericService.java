package com.qonsult.generic;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericService <B, D, I, R extends GenericRepository<B, I>, M extends GenericMapper<B, D>> {
    Mono<D> findById(I id);
    Mono<B> findBoById(I id);
    Flux<D> findAll(Specification specification);
    Flux<D> findAll(final Specification specification, Pageable pageable);
    Flux<D> findAll();
    Mono<D> saveDTO(D dto);
    Mono<B> saveEntity(B bo);
    Mono<D> delete(I id);
    Mono<D> update(I id, D dto);
}
