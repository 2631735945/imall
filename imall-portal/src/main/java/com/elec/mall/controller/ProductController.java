package com.elec.mall.controller;

import com.elec.mall.pojo.Product;
import com.elec.mall.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;


    @RequestMapping("/selectById")
    @ResponseBody
    public Product selectById(Integer id) {
        return productService.selectById(id);
    }

    @RequestMapping("/getProductListPage")
    public String getProductListPage(Integer id, Model model){
        List<Product> list = productService.selectListByCategoryId(id);
        model.addAttribute("list", list);
        return "product_list";
    }

    @RequestMapping("/getProductDetailPage")
    public String getProductDetailPage(Integer id, Model model){
        Product product = productService.selectById(id);
        model.addAttribute("product", product);
        return "product_detail";
    }
}