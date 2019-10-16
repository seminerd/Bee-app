package vn.beemart.service.data.mssql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.beemart.service.data.mssql.dto.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAddressesByAccountId(int accountId);
}
