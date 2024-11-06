package com.alimert.service;

import com.alimert.dto.DtoAccount;
import com.alimert.dto.DtoAccountIU;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
