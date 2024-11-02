package com.alimert.service;

import com.alimert.dto.AuthRequest;
import com.alimert.dto.DtoUser;

public interface IAuthenticationService {
    public DtoUser register(AuthRequest authRequest);
}
