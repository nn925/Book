package com.jn.dao;

import com.jn.bean.OrderItem;

/**
 * @author jiangna27602
 */
public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    public int saveOrderItem(OrderItem orderItem);
}
