package com.elec.mall.service;

import com.elec.mall.pojo.User;

public interface IUserService {
    User login(String name, String password);
}
