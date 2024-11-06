package com.alimert.service.impl;

import com.alimert.dto.DtoAccount;
import com.alimert.dto.DtoAccountIU;
import com.alimert.model.Account;
import com.alimert.repository.AccountRepository;
import com.alimert.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;


    private Account createAccount(DtoAccountIU dtoAccountIU) {
        Account account = new Account();
        account.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoAccountIU, account);
        return account;
    }

    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
        Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));
        DtoAccount dtoAccount = new DtoAccount();
        BeanUtils.copyProperties(savedAccount, dtoAccount);


        return dtoAccount;
    }
}
