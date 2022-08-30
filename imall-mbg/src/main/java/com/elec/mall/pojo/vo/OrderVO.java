package com.elec.mall.pojo.vo;

import com.elec.mall.pojo.OrderItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderVO {
    private Long orderNo;
    // 订单总价格
    private BigDecimal payment;
    // 订单状态
    private Integer status;
    private Date createTime;

    private List<OrderItem> list;

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<OrderItem> getList() {
        return list;
    }

    public void setList(List<OrderItem> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "orderNo=" + orderNo +
                ", payment=" + payment +
                ", status=" + status +
                ", createTime=" + createTime +
                ", list=" + list +
                '}';
    }
}
