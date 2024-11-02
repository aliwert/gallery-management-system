package com.alimert.service.impl;

import com.alimert.dto.AuthRequest;
import com.alimert.dto.DtoUser;
import com.alimert.model.User;
import com.alimert.repository.UserRepository;
import com.alimert.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private User createUser(AuthRequest req) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(req.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(req.getPassword()));

        return user;

    }

    @Override
    public DtoUser register(AuthRequest authRequest) {
        DtoUser dtoUser = new DtoUser();

        User savedUser = userRepository.save(createUser(authRequest));

        BeanUtils.copyProperties(savedUser, dtoUser);
        return dtoUser;
    }
}
