package vn.beemart.service.data.mssql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.beemart.service.data.mssql.dto.Inventory;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    @Query(value = "select * from bee_inventories where variant_id = ?1 ;", nativeQuery = true)
    List<Inventory> findByVariantId(Integer id);

    @Query(value = "select * from bee_inventories where location_id = ?1 ;", nativeQuery = true)
    List<Inventory> findByLocationId(Integer id);

    @Query(value = "select * from bee_inventories where location_id = :lid and variant_id= :vid ;", nativeQuery = true)
    Inventory findByLocationIdAndVariantId(@Param("lid") Integer locationId, @Param("vid") Integer VariantId);


}
