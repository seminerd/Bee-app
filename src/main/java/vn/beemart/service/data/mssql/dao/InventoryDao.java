package vn.beemart.service.data.mssql.dao;

import vn.beemart.service.data.mssql.dto.Inventory;

import java.util.List;

public interface InventoryDao {
    List<Inventory> getByLocationId(Integer id);

    List<Inventory> getByVariantId(Integer id);

    Inventory getByLocationIdAndVariantId(Integer lid, Integer vid);
}
