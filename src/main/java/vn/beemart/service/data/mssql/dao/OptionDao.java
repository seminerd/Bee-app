package vn.beemart.service.data.mssql.dao;

import vn.beemart.service.data.mssql.dto.Option;

import java.util.List;

public interface OptionDao {
    List<Option> getByProductId(Integer id);

    Option getById(Integer id);
}
