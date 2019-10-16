package vn.beemart.service.data.mssql.dao;

import vn.beemart.service.data.mssql.dto.Checkout;

public interface CheckoutDao {
    Checkout getById(int id);

    boolean checkValidCheckout(int checkoutId);

    Checkout store(Checkout checkout);
}
