package vn.beemart.service.data.mssql.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dto.Image;
import vn.beemart.service.data.mssql.repository.ImageRepository;

import java.util.List;

@Service
public class ImageDaoImpl implements vn.beemart.service.data.mssql.dao.ImageDao {
    private ImageRepository imageRepository;
    @Autowired
    public ImageDaoImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image getById(Integer id) {
        return imageRepository.findById(id).get();
    }

    @Override
    public List<Image> getByProductId(Integer id) {
        return imageRepository.findByProductId(id);
    }
}
