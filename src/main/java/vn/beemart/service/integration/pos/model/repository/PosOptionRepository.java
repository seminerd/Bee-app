package vn.beemart.service.integration.pos.model.repository;

import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.beemart.service.integration.pos.model.dto.PosOption;
@Repository
public interface PosOptionRepository extends JpaRepository<PosOption, Integer> {
}
