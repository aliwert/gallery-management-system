package com.alimert.service;

import com.alimert.dto.DtoAccount;
import com.alimert.dto.DtoAccountIU;

import java.util.List;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
    public DtoAccount findAccountById(Long id);
    public List<DtoAccount> findAllAccounts();
}
