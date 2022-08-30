package com.elec.mall.service.impl;

import com.elec.mall.mapper.UserMapper;
import com.elec.mall.pojo.User;
import com.elec.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }
}
