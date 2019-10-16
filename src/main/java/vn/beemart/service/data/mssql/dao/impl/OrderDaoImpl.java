package vn.beemart.service.data.mssql.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.OrderDao;
import vn.beemart.service.data.mssql.dto.Order;
import vn.beemart.service.data.mssql.repository.OrderRepository;

import java.util.List;

@Service
public class OrderDaoImpl implements OrderDao {
    private final OrderRepository orderRepository;

    public OrderDaoImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order store(Order newOrder) {
        return orderRepository.saveAndFlush(newOrder);
    }

    @Override
    public Order getById(int orderId) {
        return orderRepository.getOne(orderId);
    }

    @Override
    public List<Order> getOrdersHasNotSyncWithPos() {
        return orderRepository.findOrdersByHasSyncWithPosIsFalse();
    }

    @Override
    public List<Order> getOrdersHasSyncWithPos() {
        return orderRepository.findOrdersByHasSyncWithPosIsTrue();
    }

    @Override
    public List<Order> getOrdersByAccountId(int accountId) {
        return orderRepository.findOrdersByAccountId(accountId);
    }
}
