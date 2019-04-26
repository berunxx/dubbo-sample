package com.sample.authserver.service.impl;

import com.sample.authserver.mapper.SysUserMapper;
import com.sample.authserver.pojo.SysUser;
import com.sample.authserver.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper customMapper;

    @Override
    public SysUser getUserDetails(String username) {
        return customMapper.getByUsername(username);
    }
}
