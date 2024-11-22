package com.alimert.service.impl;

import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoAccount;
import com.alimert.dto.DtoAccountIU;
import com.alimert.exception.BaseException;
import com.alimert.exception.ErrorMessage;
import com.alimert.exception.MessageType;
import com.alimert.model.Account;
import com.alimert.repository.AccountRepository;
import com.alimert.service.IAccountService;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public DtoAccount findAccountById(Long id) {
        DtoAccount dtoAccount = new DtoAccount();
        Optional<Account> accOptional = accountRepository.findById(id);
        if (accOptional.isEmpty()) {
            return null;
        }
        Account account = accOptional.get();
        BeanUtils.copyProperties(account, dtoAccount);

        return dtoAccount;
    }

    @Override
    public List<DtoAccount> findAllAccounts() {
        List<DtoAccount> dtoAccounts = new ArrayList<>();
        List<Account> accountList = accountRepository.findAll();
        if (accountList != null && !accountList.isEmpty()) {
            for (Account account : accountList) {
                DtoAccount dtoAccount = new DtoAccount();
                BeanUtils.copyProperties(account, dtoAccount);
                dtoAccounts.add(dtoAccount);
            }
        }
        return dtoAccounts;
    }

    @Override
    public DtoAccount updateAccount(Long id, DtoAccountIU dtoAccountIU) {

        DtoAccount dtoAccount = new DtoAccount();
        Optional<Account> accOptional = accountRepository.findById(id);
        if (accOptional.isPresent()) {
            Account dbAccount = accOptional.get();
            dbAccount.setAmount(dtoAccountIU.getAmount());
            dbAccount.setAccountNo(dtoAccountIU.getAccountNo());
            dbAccount.setIban(dtoAccountIU.getIban());
            dbAccount.setCurrencyType(dtoAccountIU.getCurrencyType());
            Account updateAccount = accountRepository.save(dbAccount);
            BeanUtils.copyProperties(updateAccount, dtoAccount);
            return dtoAccount;
        }
        return null;
    }

    @Override
    public RootEntity<Void> deleteAccount(Long id) {
        Optional<Account> optAccount = accountRepository.findById(id);
        if(optAccount != null) {
            accountRepository.deleteById(id);
        } else {
            throw new OpenApiResourceNotFoundException("Account not found");
        }
        return null;
    }
}
