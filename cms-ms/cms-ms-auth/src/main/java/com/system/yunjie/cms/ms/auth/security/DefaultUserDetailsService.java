package com.system.yunjie.cms.ms.auth.security;

import com.system.yunjie.cms.ms.auth.mapper.UserMapper;
import com.system.yunjie.cms.ms.auth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    private UserMapper repository;

    @Autowired
    public DefaultUserDetailsService(final UserMapper repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.selectByUsername(username);
        if (isNull(user)) {
            throw new UsernameNotFoundException("can not find user");
        }
        return withUsername(username).password(user.getPassword()).authorities(emptyList()).build();
    }
}
