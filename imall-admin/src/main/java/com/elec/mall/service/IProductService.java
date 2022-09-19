package com.elec.mall.service;

import com.elec.mall.pojo.Product;
import com.elec.mall.util.LayUITableJSONResult;


public interface IProductService {
    Product selectById(Integer id);

    LayUITableJSONResult selectByPage(Integer page, Integer limit);

    void add(Product product);
}
