package com.alimert.controller;

import com.alimert.dto.AuthRequest;
import com.alimert.dto.DtoUser;

public interface IAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest authRequest);
}
