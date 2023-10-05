package com.jn.dao.impl;

import com.jn.bean.OrderItem;
import com.jn.dao.OrderItemDao;

/**
 * @author jiangna27602
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),
                        orderItem.getOrderId());
    }
}
