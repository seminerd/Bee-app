package vn.beemart.service.data.mssql.dao;

import vn.beemart.service.data.mssql.dto.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllWithPage(Integer page);

    List<Product> getByBrandWithPage(String brand, Integer page);

    Product getById(Integer id);
    List<Product> findByBrand(String brand);


}
