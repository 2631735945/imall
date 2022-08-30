package com.elec.mall.mapper;

import com.elec.mall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(@Param("name") String name, @Param("password") String password);
}