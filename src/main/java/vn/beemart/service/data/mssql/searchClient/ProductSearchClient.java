package vn.beemart.service.data.mssql.searchClient;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import vn.beemart.service.data.mssql.dto.Product;

import java.util.List;

public interface ProductSearchClient extends ElasticsearchCrudRepository<Product,String> {
    List<Product> findByBrand(String brand);
}
