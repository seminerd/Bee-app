package vn.beemart.service.data.mssql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.beemart.service.data.mssql.dto.Image;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> {
    @Query(value = "select * from bee_images where product_id = ?1 ",nativeQuery = true)
    List<Image> findByProductId(Integer id);
}
