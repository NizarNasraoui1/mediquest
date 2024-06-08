package com.qonsult.service.impl;

import com.qonsult.dto.AccountDTO;
import com.qonsult.entity.auth.Account;
import com.qonsult.mapper.AccountMapper;
import com.qonsult.repository.AccountRepository;
import com.qonsult.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Account addNewAccount(AccountDTO accountDTO) throws Exception {
        if(this.accountRepository.findByName(accountDTO.getName()).isPresent()){
            throw new Exception("Center already exists");
        }
        Account account = this.accountMapper.toBo(accountDTO);
        return accountRepository.save(account);
    }
}
