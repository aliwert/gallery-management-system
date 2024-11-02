package com.alimert.controller.impl;


import com.alimert.controller.BaseController;
import com.alimert.controller.IAuthenticationController;
import com.alimert.controller.RootEntity;
import com.alimert.dto.AuthRequest;
import com.alimert.dto.DtoUser;
import com.alimert.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationControllerImpl extends BaseController implements IAuthenticationController {

    @Autowired
    public IAuthenticationService authenticationService;

    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest authRequest) {
        return ok(authenticationService.register(authRequest));
    }
}
