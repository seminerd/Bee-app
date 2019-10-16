package vn.beemart.service.data.mssql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.beemart.service.data.mssql.dto.Variant;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VariantRepository extends PagingAndSortingRepository<Variant, Integer> {
    @Query(value = "select * from bee_variants where product_id = ?1 ;", nativeQuery = true)
    List<Variant> findByProductId(Integer id);

    @Query(value = "select * from bee_variants where price > ?1 ;", nativeQuery = true)
    List<Variant> findByMinPrice(BigDecimal minPrice);

    @Query(value = "select * from bee_variants where price < ?1  ;", nativeQuery = true)
    List<Variant> findByMaxPrice(BigDecimal maxPrice);

    @Query(value = "select * from bee_variants where price > :minprice and price < :maxprice ;", nativeQuery = true)
    List<Variant> findByPriceSegment(@Param("minprice") BigDecimal minPrice, @Param("maxprice") BigDecimal maxPrice);


}
