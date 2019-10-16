package vn.beemart.service.data.mssql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.beemart.service.data.mssql.dto.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrdersByHasSyncWithPosIsFalse();
    List<Order> findOrdersByHasSyncWithPosIsTrue();
    List<Order> findOrdersByAccountId(int accountId);
}
