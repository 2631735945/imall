package com.elec.mall.service;

import com.elec.mall.pojo.Product;
import com.elec.mall.util.JSONResult;
import com.elec.mall.util.LayUITableJSONResult;


public interface IProductService {
    Product selectById(Integer id);

    LayUITableJSONResult selectByPage(Integer page, Integer limit);

    JSONResult add(Product product);
}
