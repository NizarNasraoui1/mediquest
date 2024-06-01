package com.qonsult.service;

import com.qonsult.dto.AccountDTO;
import com.qonsult.entity.Account;

public interface AccountService {
    Account addCenter(AccountDTO accountDTO) throws Exception;
}
