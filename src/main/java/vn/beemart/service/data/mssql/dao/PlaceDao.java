package vn.beemart.service.data.mssql.dao;

import vn.beemart.service.data.mssql.dto.Place;

import java.util.List;

public interface PlaceDao {
    boolean checkValidCity(int cityId);
    boolean checkValidCityAndDistrict(int cityId, int districtId);
    boolean checkValidCityAndDistrictAndWard(int cityId, int districtId, int wardId);
    List<Place> getPlacesByCityId(int cityId);
    List<Place> getPlacesByCityAndDistrict(int cityId, int districtId);
    Place getPlacesByCityAndDistrictAndWard(int cityId, int districtId, int wardId);
    List<Place> getAll();
}
