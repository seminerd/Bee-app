package vn.beemart.service.data.mssql.dao.impl;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.VariantDao;
import vn.beemart.service.data.mssql.dto.Variant;
import vn.beemart.service.data.mssql.repository.VariantRepository;

import java.util.List;

@Service
public class VariantDaoImpl implements VariantDao {
    private final Integer PAGE_LIMIT = 250;

    private VariantRepository variantRepository;

    @Autowired
    public VariantDaoImpl(VariantRepository variantRepository) {
        this.variantRepository = variantRepository;
    }

    @Override
    public Variant getById(Integer id) {
        return variantRepository.findById(id).get();
    }

    @Override
    public List<Variant> getAllWithPage(Integer page) {
        val newPage = PageRequest.of(page, PAGE_LIMIT);
        return variantRepository.findAll(newPage).getContent();
    }

    @Override
    public List<Variant> getByProductId(Integer productId) {
        return variantRepository.findByProductId(productId);
    }
}
