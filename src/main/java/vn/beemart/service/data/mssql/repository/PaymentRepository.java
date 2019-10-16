package vn.beemart.service.data.mssql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.beemart.service.data.mssql.dto.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
