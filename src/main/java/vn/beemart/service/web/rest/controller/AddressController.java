package vn.beemart.service.web.rest.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import vn.beemart.service.common.controller.BaseController;
import vn.beemart.service.common.validate.NotFoundException;
import vn.beemart.service.data.mssql.dao.AddressDao;
import vn.beemart.service.data.mssql.dao.PlaceDao;
import vn.beemart.service.data.mssql.dto.Address;
import vn.beemart.service.data.mssql.dto.Place;
import vn.beemart.service.data.mssql.service.AddressService;
import vn.beemart.service.data.mssql.service.PlaceService;
import vn.beemart.service.web.rest.model.address.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CommonsLog(topic = "services")
@RequestMapping("/addresses")
public class AddressController extends BaseController {
    private final PlaceDao placeDao;

    private final AddressDao addressDao;

    private final PlaceService placeService;

    private final AddressService addressService;

    public AddressController(PlaceDao placeDao, AddressDao addressDao, PlaceService placeService, AddressService addressService) {
        this.placeDao = placeDao;
        this.addressDao = addressDao;
        this.placeService = placeService;
        this.addressService = addressService;
    }

    @ApiOperation(value = "Lấy thông tin các địa chỉ giao hàng")
    @GetMapping
    public ListAddressesResponse getAddress(@RequestParam(name = "X-Sapo-AccountId") Integer accountId) {
        try {
            val addresses = addressDao.getAllAddressesByAccountId(accountId);
            val addressesResponse = addresses.stream().map(value -> mapperFacade.map(value, AddressResponse.class)).collect(Collectors.toList());
            return new ListAddressesResponse(addressesResponse);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @ApiOperation(value = "Lấy thông tin một địa chỉ giao hàng cụ thể dựa vào id")
    @GetMapping("/{address_id}")
    public AddressResponse getAddress(@PathVariable(value = "address_id") int addressId) {
        try {
            return addressService.getAddressResponse(addressId);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @ApiOperation(value = "Thêm địa chỉ giao hàng", notes = "Wrap json body bên dưới với json root {\"address\": {model_below}}")
    @PostMapping
    public AddressResponse addAddress(@RequestBody @Valid AddressRequest model) {
        val newAddress = new Address();
        newAddress.setAccountId(model.getAccountId());
        newAddress.setAddress(model.getAddress());
        newAddress.setPhoneNumber(model.getPhoneNumber());
        try {
            String cityName = placeService.getCityName(model.getCityId());
            newAddress.setCity(cityName);
            newAddress.setCityId(model.getCityId());
            String districtName = placeService.getDistrictName(model.getCityId(), model.getDistrictId());
            newAddress.setDistrict(districtName);
            newAddress.setDistrictId(model.getDistrictId());
            String wardName = placeService.getWardName(model.getCityId(), model.getDistrictId(), model.getWardId());
            newAddress.setWard(wardName);
            newAddress.setWardId(model.getWardId());
            addressDao.store(newAddress);
            return addressService.getAddressResponse(newAddress.getAddressId());
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @ApiOperation(value = "Cập nhật địa chỉ giao hàng", notes = "Wrap json body bên dưới với json root {\"address\": {model_below}}")
    @PostMapping("/{address_id}")
    public AddressResponse addAddress(@PathVariable(value = "address_id") int addressId,
                                      @RequestBody @Valid AddressRequest model) {
        if(!addressDao.checkValidAddress(addressId)) {
            throw new NotFoundException("Địa chỉ không tồn tại");
        }
        val address = addressDao.getById(addressId);
        address.setAccountId(model.getAccountId());
        address.setAddress(model.getAddress());
        address.setPhoneNumber(model.getPhoneNumber());
        try {
            String cityName = placeService.getCityName(model.getCityId());
            address.setCity(cityName);
            address.setCityId(model.getCityId());
            String districtName = placeService.getDistrictName(model.getCityId(), model.getDistrictId());
            address.setDistrict(districtName);
            address.setCityId(model.getDistrictId());
            String wardName = placeService.getWardName(model.getCityId(), model.getDistrictId(), model.getWardId());
            address.setWard(wardName);
            address.setWardId(model.getWardId());
            addressDao.store(address);
            return addressService.getAddressResponse(address.getAddressId());
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @ApiOperation(value = "Xóa một địa chỉ giao hàng")
    @DeleteMapping("/{address_id}")
    public void deleteAddress(@PathVariable(value = "address_id") int addressId) {
        if(!addressDao.checkValidAddress(addressId)) {
            throw new NotFoundException("Địa chỉ không tồn tại");
        }
        addressDao.remove(addressId);
    }

    @ApiOperation(value = "Lấy thông tin các tỉnh/thành phố")
    @GetMapping("/cities")
    public ListPlacesResponse getCities() {
        try {
            val places = placeDao.getAll();
            val placesResponse = getPlacesResponseFromPlaces(places);
            return new ListPlacesResponse(placesResponse);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @ApiOperation(value = "Lấy thông tin các quận/huyện của tỉnh/thành phố")
    @GetMapping("/cities/{city_id}/districts")
    public ListPlacesResponse getDistricts(@PathVariable("city_id") int cityId) {
        try {
            val places = placeDao.getPlacesByCityId(cityId);
            val placesResponse = getPlacesResponseFromPlaces(places);
            return new ListPlacesResponse(placesResponse);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @ApiOperation(value = "Lấy thông tin các phường/xã của quận/huyện")
    @GetMapping("/cities/{city_id}/districts/{district_id}/wards")
    public ListPlacesResponse getWards(@PathVariable("city_id") int cityId,
                                  @PathVariable("district_id") int districtId) {
        try {
            val places = placeDao.getPlacesByCityAndDistrict(cityId, districtId);
            val placesResponse = getPlacesResponseFromPlaces(places);
            return new ListPlacesResponse(placesResponse);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    private List<PlaceResponse> getPlacesResponseFromPlaces(List<Place> places) {
        return places.stream().map(value -> mapperFacade.map(value, PlaceResponse.class))
                .collect(Collectors.toList());
    }
}
