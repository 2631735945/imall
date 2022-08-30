package com.elec.mall.mapper;

import com.elec.mall.pojo.Order;
import com.elec.mall.pojo.vo.OrderVO;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Long orderNo);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderVO> selectByUserId(Integer id);

}