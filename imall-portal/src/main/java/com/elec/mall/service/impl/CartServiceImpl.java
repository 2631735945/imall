package com.elec.mall.service.impl;

import com.elec.mall.mapper.CartMapper;
import com.elec.mall.pojo.Cart;
import com.elec.mall.pojo.vo.CartVO;
import com.elec.mall.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public void add(Cart cart) {
        // 1、根据user_id和product_id去cart看这个用户是不是已经有这个商品
        // 2、如果这个购物车里面已经有了，只要更新一下数量
        // 3、如果没有才执行inert
        int count = cartMapper.selectCountByUserIdAndProductId(cart);
        if (count >= 1) {
            cartMapper.updateQuantityByUserIdAndProductId(cart);
        } else {
            cartMapper.insert(cart);
        }
    }

    @Override
    public List<CartVO> selectByUserId(Integer id) {
        return cartMapper.selectByUserId(id);
    }

    @Override
    public void updateChecked(Integer id, Integer checked) {
        cartMapper.updateChecked(id, checked);
    }

    @Override
    public List<CartVO> selectByUserIdAndChecked(Integer id) {
        return cartMapper.selectByUserIdAndChecked(id);
    }

    @Override
    public void deleteById(Integer id) {
        cartMapper.deleteByPrimaryKey(id);
    }
}
