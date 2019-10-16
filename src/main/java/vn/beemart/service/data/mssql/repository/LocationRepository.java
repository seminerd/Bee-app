package vn.beemart.service.data.mssql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.beemart.service.data.mssql.dto.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
