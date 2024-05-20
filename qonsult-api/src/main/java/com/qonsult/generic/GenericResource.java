package com.qonsult.generic;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public abstract class GenericResource<B, D, I, R extends GenericRepository<B, I>, M extends GenericMapper<B, D>, S extends GenericService<B, D, I, R, M>> {
    protected final S service;



    @PostMapping
    Mono<D> save(@RequestBody D dto){
        return service.saveDTO(dto);
    }

    @GetMapping("/{id}")
    Mono<D> getById(@PathVariable("id") I id){
        return service.findById(id);
    }

    @GetMapping("/all")
    Flux<D> getById(){
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    Mono<D>delete(@PathVariable("id") I id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    Mono<D>update(@PathVariable("id") I id,@RequestBody D dto){
        return service.update(id,dto);
    }
}
