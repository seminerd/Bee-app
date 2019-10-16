package vn.beemart.service.data.mssql.service;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.AddressDao;
import vn.beemart.service.web.rest.model.address.AddressResponse;

@Service
public class AddressService {
    private final MapperFacade mapperFacade;

    private final AddressDao addressDao;

    public AddressService(MapperFacade mapperFacade, AddressDao addressDao) {
        this.mapperFacade = mapperFacade;
        this.addressDao = addressDao;
    }

    public AddressResponse getAddressResponse(int addressId) throws Exception {
        if(!addressDao.checkValidAddress(addressId)) {
            throw new Exception("Địa chỉ không tồn tại");
        }
        return mapperFacade.map(addressDao.getById(addressId), AddressResponse.class);
    }
}
