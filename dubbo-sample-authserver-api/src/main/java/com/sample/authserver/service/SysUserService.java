package com.sample.authserver.service;

import com.sample.authserver.pojo.SysUser;

public interface SysUserService {
    SysUser getUserDetails(String username);
}
