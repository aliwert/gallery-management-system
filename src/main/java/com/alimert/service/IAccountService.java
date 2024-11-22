package com.alimert.service;

import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoAccount;
import com.alimert.dto.DtoAccountIU;

import java.util.List;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
    public DtoAccount findAccountById(Long id);
    public List<DtoAccount> findAllAccounts();
    public DtoAccount updateAccount(Long id, DtoAccountIU dtoAccountIU);
    public RootEntity<Void> deleteAccount(Long id);
}
