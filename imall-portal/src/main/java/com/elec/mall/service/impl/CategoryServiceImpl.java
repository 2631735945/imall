package com.elec.mall.service.impl;

import com.elec.mall.mapper.CategoryMapper;
import com.elec.mall.pojo.Category;
import com.elec.mall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    @Override
    public List<Category> selectTopCategoryList() {
        return categoryMapper.selectTopCategoryList();
    }

    @Override
    public List<Category> selectSecondCategoryList() {
        return categoryMapper.selectSecondCategoryList();
    }
}
