package com.elec.mall.service;

import com.elec.mall.pojo.Order;
import com.elec.mall.pojo.vo.OrderVO;

import java.util.List;

public interface IOrderService {
    void add(Order order);

    List<OrderVO> selectByUserId(Integer id);
}
