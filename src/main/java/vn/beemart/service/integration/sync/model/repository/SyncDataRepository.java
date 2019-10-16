package vn.beemart.service.integration.sync.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.beemart.service.integration.sync.model.dto.SyncModel;
@Repository
public interface SyncDataRepository extends JpaRepository<SyncModel,Integer> {
}
