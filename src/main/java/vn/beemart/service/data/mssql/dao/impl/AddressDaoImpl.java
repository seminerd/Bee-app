package vn.beemart.service.data.mssql.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.AddressDao;
import vn.beemart.service.data.mssql.dto.Address;
import vn.beemart.service.data.mssql.repository.AddressRepository;

import java.util.List;

@Service
public class AddressDaoImpl implements AddressDao {
    private final AddressRepository addressRepository;

    public AddressDaoImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address getById(int id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public boolean checkValidAddress(int addressId) {
        return addressRepository.existsById(addressId);
    }

    @Override
    public List<Address> getAllAddressesByAccountId(int accountId) {
        return addressRepository.findAddressesByAccountId(accountId);
    }

    @Override
    public void remove(int addressId) {
        addressRepository.deleteById(addressId);
    }

    @Override
    public Address store(Address newAddress) {
        return addressRepository.saveAndFlush(newAddress);
    }
}
