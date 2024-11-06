package com.alimert.controller.impl;

import com.alimert.controller.BaseController;
import com.alimert.controller.IAccountController;
import com.alimert.controller.RootEntity;
import com.alimert.dto.DtoAccount;
import com.alimert.dto.DtoAccountIU;
import com.alimert.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
