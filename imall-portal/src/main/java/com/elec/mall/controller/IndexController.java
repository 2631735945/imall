package com.elec.mall.controller;

import com.elec.mall.pojo.Category;
import com.elec.mall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class IndexController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private RedisTemplate redisTemplate;

    private Map<String, List<Category>> map = new HashMap<>();

    // 设置项目默认的首页（欢迎页）
    // http://localhost:8080/
    // 如果是80端口号，可以不用写
    // http://localhost/
    @RequestMapping("/")
    public String index(Model model) {
        System.out.println("IndexController.index");
        /*List<Category> topCategoryList = map.get("topCategoryList");
        if (CollectionUtils.isEmpty(topCategoryList)) {
            topCategoryList = categoryService.selectTopCategoryList();
            map.put("topCategoryList", topCategoryList);
        }
        List<Category> secondCategoryList = map.get("secondCategoryList");
        if (CollectionUtils.isEmpty(secondCategoryList)) {
            secondCategoryList = categoryService.selectSecondCategoryList();
            map.put("secondCategoryList", secondCategoryList);
        }*/

        // 1.首先从Redis取数据
        List<Category> topCategoryList = redisTemplate.opsForList().range("topCategoryList", 0, -1);
        // 2、Redis中没有
        if (CollectionUtils.isEmpty(topCategoryList)) {
            System.out.println("Redis中没有topCategoryList，从数据库中取");
            topCategoryList = categoryService.selectTopCategoryList();
            //3、数据库中取出来后，更新到Redis中
            redisTemplate.opsForList().rightPushAll("topCategoryList", topCategoryList);
        }

        List<Category> secondCategoryList = redisTemplate.opsForList().range("secondCategoryList", 0, -1);
        if (CollectionUtils.isEmpty(secondCategoryList)) {
            System.out.println("Redis中没有secondCategoryList，从数据库中取");
            secondCategoryList = categoryService.selectSecondCategoryList();
            redisTemplate.opsForList().rightPushAll("secondCategoryList", secondCategoryList);
        }

        model.addAttribute("topCategoryList", topCategoryList);
        model.addAttribute("secondCategoryList", secondCategoryList);
        return "index";
    }
}