package vn.beemart.service.data.mssql.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.PlaceDao;

@Service
public class PlaceService {
    private final PlaceDao placeDao;

    public PlaceService(PlaceDao placeDao) {
        this.placeDao = placeDao;
    }

    public String getCityName(int cityId) throws Exception {
        val places = placeDao.getPlacesByCityId(cityId);
        if(places.isEmpty()) {
            throw new Exception("Thành phố có city_id:"+cityId+" không tồn tại");
        }
        return places.get(0).getCityName();
    }

    public String getDistrictName(int cityId, int districtId) throws Exception {
        val places = placeDao.getPlacesByCityAndDistrict(cityId, districtId);
        if(places.isEmpty()) {
            throw new Exception("Quận/huyện có city_id:"+cityId+" và district_id:"+districtId+" không tồn tại");
        }
        return places.get(0).getDistrictName();
    }

    public String getWardName(int cityId, int districtId, int wardId) throws Exception {
        val place = placeDao.getPlacesByCityAndDistrictAndWard(cityId, districtId, wardId);
        if(place == null) {
            throw new Exception("Phường/xã có city_id:"+cityId+" và district_id:"+districtId+" và ward_id:"+wardId+" không tồn tại");
        }
        return place.getWardName();
    }
}
