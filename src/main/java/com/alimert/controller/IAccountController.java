package com.alimert.controller;

import com.alimert.dto.DtoAccount;
import com.alimert.dto.DtoAccountIU;

import java.util.List;


public interface IAccountController {


    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
    public RootEntity<DtoAccount> findAccountById(Long id);
    public RootEntity<List<DtoAccount>> findAllAccounts();
    public RootEntity<DtoAccount> updateAccount(Long id, DtoAccountIU dtoAccountIU);
}
