package com.qonsult.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<B, I> extends JpaRepository<B, I>, JpaSpecificationExecutor<B>{
    //Empty
}

