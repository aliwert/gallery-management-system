package com.alimert.service.impl;

import com.alimert.dto.AuthRequest;
import com.alimert.dto.AuthResponse;
import com.alimert.dto.DtoUser;
import com.alimert.exception.BaseException;
import com.alimert.exception.ErrorMessage;
import com.alimert.exception.MessageType;
import com.alimert.jwt.JwtService;
import com.alimert.model.RefreshToken;
import com.alimert.model.User;
import com.alimert.repository.RefreshTokenRepository;
import com.alimert.repository.UserRepository;
import com.alimert.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    private User createUser(AuthRequest req) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(req.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(req.getPassword()));

        return user;
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 6));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        return refreshToken;
    }

    @Override
    public DtoUser register(AuthRequest authRequest) {
        DtoUser dtoUser = new DtoUser();

        User savedUser = userRepository.save(createUser(authRequest));

        BeanUtils.copyProperties(savedUser, dtoUser);
        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            authenticationProvider.authenticate(authenticationToken);
            Optional<User> byUsername = userRepository.findByUsername(authRequest.getUsername());
            String accessToken = jwtService.generateToken(byUsername.get());
            RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(byUsername.get()));
            return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());

        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND, e.getMessage()));
        }
    }
}
