package com.jn.service.impl;

import com.jn.bean.Cart;
import com.jn.bean.CartItem;
import com.jn.bean.Order;
import com.jn.bean.OrderItem;
import com.jn.dao.OrderDao;
import com.jn.dao.OrderItemDao;
import com.jn.dao.impl.OrderDaoImpl;
import com.jn.dao.impl.OrderItemDaoImpl;
import com.jn.service.OrderService;

import java.util.Date;
import java.util.Map;


/**
 * @author jiangna27602
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //创建唯一订单号
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);
        }
        cart.clear();
        return orderId;
    }
}
