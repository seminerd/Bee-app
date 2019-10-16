package vn.beemart.service.data.mssql.dao;

import vn.beemart.service.data.mssql.dto.Address;

import java.util.List;

public interface AddressDao {
    Address getById(int id);

    boolean checkValidAddress(int addressId);

    List<Address> getAllAddressesByAccountId(int accountId);

    void remove(int addressId);

    Address store(Address newAddress);
}
