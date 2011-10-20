package com.spshop.service.intf;

import java.util.List;

import com.spshop.dao.intf.OrderDAO;
import com.spshop.model.Order;

public interface OrderService extends BaseService<Order,OrderDAO, Long>{
	public Order saveOrder(Order order, String status);
	public Order getOrderById(String id);
	public List<Order> getOrdersByUserId(long userId);
}
