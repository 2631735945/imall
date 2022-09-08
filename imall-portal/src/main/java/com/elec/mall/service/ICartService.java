package com.elec.mall.service;

import com.elec.mall.pojo.Cart;
import com.elec.mall.pojo.vo.CartVO;

import java.util.List;

public interface ICartService {
    void add(Cart cart);

    List<CartVO> selectByUserId(Integer id);

    void updateChecked(Integer id, Integer checked);

    List<CartVO> selectByUserIdAndChecked(Integer id);

    void deleteById(Integer id);
}
