package com.system.yunjie.cms.ms.auth.service.impl;

import com.system.yunjie.cms.ms.auth.mapper.UserMapper;
import com.system.yunjie.cms.ms.auth.entity.User;
import com.system.yunjie.cms.ms.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(final UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void create(User user) {
        Optional<User> existing = Optional.ofNullable(userMapper.selectByUsername(user.getUsername()));
        existing.ifPresent(it -> {
            throw new IllegalArgumentException("user already exists: " + it.getUsername());
        });
        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        userMapper.insert(user);
        log.info("new user has been created: {}", user.getUsername());
    }
}
