package com.elec.mall.service;

import com.elec.mall.pojo.Product;

import java.util.List;

public interface IProductService {
    Product selectById(Integer id);

    List<Product> selectListByCategoryId(Integer id);
}
