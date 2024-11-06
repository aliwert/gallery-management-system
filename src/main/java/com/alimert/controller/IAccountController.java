package com.alimert.controller;

import com.alimert.dto.DtoAccount;
import com.alimert.dto.DtoAccountIU;



public interface IAccountController {


    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
