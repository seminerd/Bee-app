package vn.beemart.service.data.mssql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vn.beemart.service.data.mssql.dto.Product;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
    @Query(value = "select * from bee_products where brand = ?1 LIMIT 250, ?2 ;",nativeQuery = true)
    List<Product> findByBrandWithPage(String brand, Integer offset);
    @Query(value = "select * from bee_products where brand = ?1 ;",nativeQuery = true)
    List<Product> findByBrand(String brand);
}
