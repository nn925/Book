package com.jn.service;

import com.jn.bean.Cart;
import com.jn.bean.Order;

/**
 * @author jiangna27602
 */
public interface OrderService {
    /**
     * 生成订单
     * @param cart
     * @param userId
     * @return 返回订单号
     */
    public String createOrder(Cart cart,Integer userId);
}
