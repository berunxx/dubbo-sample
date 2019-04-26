package com.sample.authserver.service.impl;

import com.sample.authserver.pojo.CustomUser;
import com.sample.authserver.pojo.SysUser;
import com.sample.authserver.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailsService implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = null;
        try {
            user = sysUserService.getUserDetails(username);
            CustomUser customUser = new CustomUser(user);
            return customUser;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    }
}
