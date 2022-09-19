package com.elec.mall.service.impl;

import com.elec.mall.mapper.CartMapper;
import com.elec.mall.mapper.OrderItemMapper;
import com.elec.mall.mapper.OrderMapper;
import com.elec.mall.pojo.Order;
import com.elec.mall.pojo.OrderItem;
import com.elec.mall.pojo.vo.CartVO;
import com.elec.mall.pojo.vo.OrderVO;
import com.elec.mall.service.IOrderService;
import com.elec.mall.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartMapper cartMapper ;
    @Autowired
    private SnowFlake snowFlake;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public void add(Order order) {
        // 现在这个order里面已经有的信息：userId、shippingId、paymentType
        // 使用雪花算法生成订单的主键
        long id = snowFlake.nextId();
        order.setOrderNo(id);
        // 订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
        order.setStatus(10);

        // payment decimal(20,2)实际付款金额,单位是元,保留两位小数

        // 购物车里面选中要去生成订单结算商品
//        根据用户id查出该用户购物车里的选中去结算的商品
        List<CartVO> cartVOList = cartMapper.selectByUserIdAndChecked(order.getUserId());
        // 整个订单的总价格
        BigDecimal payment = BigDecimal.valueOf(0.0);
        for (CartVO cartVO : cartVOList) {
//            orderItem  订单项  代表订单里的其中一个商品
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderNo(id);
            orderItem.setUserId(order.getUserId());
            orderItem.setQuantity(cartVO.getQuantity());//数量

            orderItem.setProductId(cartVO.getProductId());
            // 根据productId完全可以去product表查出来：name、price、mainImage
            //但是还是额外把这些属性加上了，这处于两个方面考虑
            // 1、效率考虑，inner join比较耗时
            // 2、当时购买的快照，因为后期这些商品信息可能会被修改，要是再根据productId去查
            //可能就和当时买的商品信息对不起来了，所以要拍一个快照，避免以后商品信息修改，或者该商品直接被下架之后，
            // 查看购买的产品的时候商品信息出问题
            orderItem.setProductName(cartVO.getProductName());
            orderItem.setCurrentUnitPrice(cartVO.getProductPrice());//单价
            orderItem.setProductImage(cartVO.getProductMainImage());
            // totalPrice = 商品价格*数量
            BigDecimal productPrice = cartVO.getProductPrice();
            BigDecimal quantity = BigDecimal.valueOf(cartVO.getQuantity());
            BigDecimal totalPrice = productPrice.multiply(quantity);
            orderItem.setTotalPrice(totalPrice);

            payment = payment.add(totalPrice);
            orderItemMapper.insert(orderItem);

            // 每次插入订单项时候，应该到购物车表里面把对应数据删除掉
            cartMapper.deleteByPrimaryKey(cartVO.getId());
        }

        order.setPayment(payment);
        orderMapper.insert(order);
    }

    @Override
    public List<OrderVO> selectByUserId(Integer id) {
        return orderMapper.selectByUserId(id);
    }
}