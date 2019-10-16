package vn.beemart.service.data.mssql.dao;

import vn.beemart.service.data.mssql.dto.Variant;

import java.util.List;

public interface VariantDao {
    Variant getById(Integer id);

    List<Variant> getAllWithPage(Integer page);

    List<Variant> getByProductId(Integer productId);
}
