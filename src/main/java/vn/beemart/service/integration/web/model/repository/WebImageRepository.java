package vn.beemart.service.integration.web.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.beemart.service.integration.web.model.dto.WebImage;
@Repository
public interface WebImageRepository extends JpaRepository<WebImage,Integer> {
}
