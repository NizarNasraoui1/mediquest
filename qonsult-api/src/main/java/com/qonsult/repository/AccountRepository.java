package com.qonsult.repository;

import com.qonsult.entity.Account;
import com.qonsult.generic.GenericRepository;

import java.util.Optional;

public interface AccountRepository extends GenericRepository<Account,Long> {
    Optional<Account> findByName(String name);
}
