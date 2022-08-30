package com.elec.mall.service.impl;

import com.elec.mall.mapper.ProductMapper;
import com.elec.mall.pojo.Product;
import com.elec.mall.service.IProductService;
import com.elec.mall.util.JSONResult;
import com.elec.mall.util.LayUITableJSONResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
    public LayUITableJSONResult selectByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Product> list = productMapper.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        long totalCount = pageInfo.getTotal();

        return LayUITableJSONResult.ok((int)totalCount,list);
    }

    @Override
    public JSONResult add(Product product) {
        productMapper.insert(product);
        return JSONResult.ok("插入成功");
    }

}
