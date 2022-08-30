package com.elec.mall.service;

import com.elec.mall.pojo.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> selectTopCategoryList();

    List<Category> selectSecondCategoryList();
}
