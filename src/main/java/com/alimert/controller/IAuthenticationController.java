package com.alimert.controller;

import com.alimert.dto.AuthRequest;
import com.alimert.dto.AuthResponse;
import com.alimert.dto.DtoUser;
import com.alimert.dto.RefreshTokenRequest;

public interface IAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest authRequest);

    public RootEntity<AuthResponse> authenticate(AuthRequest authRequest);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest tokenRequest);
}
