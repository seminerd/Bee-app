package vn.beemart.service.data.mssql.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.PlaceDao;
import vn.beemart.service.data.mssql.dto.Place;
import vn.beemart.service.data.mssql.repository.PlaceRepository;

import java.util.List;

@Service
public class PlaceDaoImpl implements PlaceDao {
    private final PlaceRepository placeRepository;

    public PlaceDaoImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public boolean checkValidCity(int cityId) {
        return (!placeRepository.findPlacesByCityId(cityId).isEmpty());
    }

    @Override
    public boolean checkValidCityAndDistrict(int cityId, int districtId) {
        return (!placeRepository.findPlacesByCityIdAndDistrictId(cityId, districtId).isEmpty());
    }

    @Override
    public boolean checkValidCityAndDistrictAndWard(int cityId, int districtId, int wardId) {
        return (placeRepository.findPlaceByCityIdAndDistrictIdAndWardId(cityId, districtId, wardId) != null);
    }

    @Override
    public List<Place> getPlacesByCityId(int cityId) {
        return placeRepository.findPlacesByCityId(cityId);
    }

    @Override
    public List<Place> getPlacesByCityAndDistrict(int cityId, int districtId) {
        return placeRepository.findPlacesByCityIdAndDistrictId(cityId, districtId);
    }

    @Override
    public Place getPlacesByCityAndDistrictAndWard(int cityId, int districtId, int wardId) {
        return placeRepository.findPlaceByCityIdAndDistrictIdAndWardId(cityId, districtId, wardId);
    }

    @Override
    public List<Place> getAll() {
        return placeRepository.findAll();
    }
}
