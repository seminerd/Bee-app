package vn.beemart.service.data.mssql.dao;

import vn.beemart.service.data.mssql.dto.Image;

import java.util.List;

public interface ImageDao {
    Image getById(Integer id);

    List<Image> getByProductId(Integer id);
}
