package vn.beemart.service.data.mssql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.beemart.service.data.mssql.dto.CartCode;

@Repository
public interface CartCodeRepository extends JpaRepository<CartCode, String> {
}
