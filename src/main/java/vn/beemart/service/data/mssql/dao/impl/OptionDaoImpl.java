package vn.beemart.service.data.mssql.dao.impl;

import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.OptionDao;
import vn.beemart.service.data.mssql.dto.Option;
import vn.beemart.service.data.mssql.repository.OptionRepository;

import java.util.List;

@Service
public class OptionDaoImpl implements OptionDao {

    private OptionRepository optionRepository;

    public OptionDaoImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }
    @Override
    public List<Option> getByProductId(Integer id){
        return optionRepository.findByProductId(id);
    }
    @Override
    public Option getById(Integer id){
        return optionRepository.findById(id).get();
    }
}
