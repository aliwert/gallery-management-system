package com.alimert.controller.impl;

import com.alimert.controller.BaseController;
import com.alimert.controller.IAccountController;
import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoAccount;
import com.alimert.dto.DtoAccountIU;
import com.alimert.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/api/account")
public class AccountControllerImpl extends BaseController implements IAccountController {

    @Autowired
    private IAccountService accountService;


    @PostMapping("/save")
    @Override
    public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return ok(accountService.saveAccount(dtoAccountIU));
    }

    @Override
    @GetMapping("/list/{id}")
    public RootEntity<DtoAccount> findAccountById(@PathVariable(name= "id")  Long id) {

        return ok(accountService.findAccountById(id));
    }

    @Override
    @GetMapping("list")
    public RootEntity<List<DtoAccount>> findAllAccounts() {
        return ok(accountService.findAllAccounts());
    }
}
