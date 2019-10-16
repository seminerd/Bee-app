package vn.beemart.service.data.mssql.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.InventoryDao;
import vn.beemart.service.data.mssql.dto.Inventory;
import vn.beemart.service.data.mssql.repository.InventoryRepository;

import java.util.List;

@Service
public class InventoryDaoImpl implements InventoryDao {
    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryDaoImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> getByLocationId(Integer id) {
        return inventoryRepository.findByLocationId(id);
    }

    @Override
    public List<Inventory> getByVariantId(Integer id) {
        return  inventoryRepository.findByVariantId(id);
    }
    @Override
    public Inventory getByLocationIdAndVariantId(Integer lid, Integer vid){
        return inventoryRepository.findByLocationIdAndVariantId(lid,vid);
    }

}
