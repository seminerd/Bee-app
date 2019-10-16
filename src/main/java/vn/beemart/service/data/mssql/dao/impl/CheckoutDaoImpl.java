package vn.beemart.service.data.mssql.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.CheckoutDao;
import vn.beemart.service.data.mssql.dto.Checkout;
import vn.beemart.service.data.mssql.repository.CheckoutRepository;

@Service
public class CheckoutDaoImpl implements CheckoutDao {
    private final CheckoutRepository checkoutRepository;

    public CheckoutDaoImpl(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    @Override
    public Checkout getById(int id) {
        return checkoutRepository.findById(id).orElse(null);
    }

    @Override
    public boolean checkValidCheckout(int checkoutId) {
        return checkoutRepository.existsById(checkoutId);
    }

    @Override
    public Checkout store(Checkout checkout) {
        return checkoutRepository.saveAndFlush(checkout);
    }
}
