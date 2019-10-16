package vn.beemart.service.integration.pos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.beemart.service.integration.pos.model.dto.PosVariant;
@Repository
public interface PosVariantRepository extends JpaRepository<PosVariant,Integer> {
}
