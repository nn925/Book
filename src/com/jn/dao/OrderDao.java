package com.jn.dao;

import com.jn.bean.Order;

/**
 * @author jiangna27602
 */
public interface OrderDao {
    /**
     * 生成订单
     * @param order
     * @return
     */
    public int saveOrder(Order order);
}
