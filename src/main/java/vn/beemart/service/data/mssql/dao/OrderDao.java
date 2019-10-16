package vn.beemart.service.data.mssql.dao;

import vn.beemart.service.data.mssql.dto.Order;

import java.util.List;

public interface OrderDao {
    Order store(Order newOrder);

    Order getById(int orderId);

    List<Order> getOrdersHasNotSyncWithPos();

    List<Order> getOrdersHasSyncWithPos();

    List<Order> getOrdersByAccountId(int accountId);
}
