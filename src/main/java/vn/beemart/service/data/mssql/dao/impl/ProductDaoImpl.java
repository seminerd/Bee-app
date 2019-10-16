package vn.beemart.service.data.mssql.dao.impl;

import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.ProductDao;
import vn.beemart.service.data.mssql.dto.Product;
import vn.beemart.service.data.mssql.repository.ProductRepository;
import vn.beemart.service.data.mssql.searchClient.ProductSearchClient;

import java.util.List;

@Service
public class ProductDaoImpl implements ProductDao {
    @Autowired
    ProductSearchClient searchClient;
    private final Integer PAGE_LIMIT = 250;
    private ProductRepository productRepository;


    public ProductDaoImpl(ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllWithPage(Integer page) {
        Pageable newPage = PageRequest.of(page, PAGE_LIMIT);
        return productRepository.findAll(newPage).getContent();
    }

    @Override
    public List<Product> getByBrandWithPage(String brand, Integer page) {
        val offset = page * PAGE_LIMIT;
        return productRepository.findByBrandWithPage(brand, offset);
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findByBrand(String brand) {
        return searchClient.findByBrand(brand);
    }


}
