package com.elec.mall.service;

import com.elec.mall.pojo.Shipping;

import java.util.List;

public interface IShippingService {
    List<Shipping> selectByUserId(Integer id);
}
