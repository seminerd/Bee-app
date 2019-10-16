package vn.beemart.service.data.mssql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.beemart.service.data.mssql.dto.Place;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findPlacesByCityId(int cityId);
    List<Place> findPlacesByCityIdAndDistrictId(int cityId, int districtId);
    Place findPlaceByCityIdAndDistrictIdAndWardId(int cityId, int districtId, int wardId);
}
