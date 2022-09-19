package com.elec.mall.controller;

import com.elec.mall.pojo.Product;
import com.elec.mall.service.IProductService;
import com.elec.mall.util.JSONResult;
import com.elec.mall.util.LayUITableJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping("/selectById")
    @ResponseBody
    public Product selectById(Integer id){
        return  productService.selectById(id);
    }

    @RequestMapping("/getProductListPage")
    public String getProductListPage(){
        return "product_list";
    }

    @RequestMapping("/selectByPage")
    @ResponseBody
    public LayUITableJSONResult selectByPage(Integer page, Integer limit){
        LayUITableJSONResult layUITableResult = productService.selectByPage(page, limit);
        return layUITableResult;
    }

    @RequestMapping("/getProductAddPage")
    public String getProductAddPage(){
        return "product_add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public JSONResult add(Product product) {
        productService.add(product);
        return JSONResult.ok("插入成功");
    }
}
