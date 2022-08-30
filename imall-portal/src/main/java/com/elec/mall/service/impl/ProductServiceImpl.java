package com.elec.mall.service.impl;

import com.elec.mall.mapper.ProductMapper;
import com.elec.mall.pojo.Product;
import com.elec.mall.service.IProductService;
import com.elec.mall.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product selectById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> selectListByCategoryId(Integer id) {
        return productMapper.selectListByCategoryId(id);
    }


}
