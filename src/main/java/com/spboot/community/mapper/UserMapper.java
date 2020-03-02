package com.spboot.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("insert into t_user(account,nickname,token) value(#{account},#{name},#{token})")
    void insert(String account, String name, String token);

}
