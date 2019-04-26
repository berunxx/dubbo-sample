package com.sample.authserver.mapper;

import com.sample.authserver.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {
    SysUser getByUsername(String username);
}
