package com.qonsult.mapper;

import com.qonsult.dto.AccountDTO;
import com.qonsult.entity.auth.Account;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper extends GenericMapper<Account, AccountDTO> {
}
