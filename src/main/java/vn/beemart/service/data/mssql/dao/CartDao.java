package vn.beemart.service.data.mssql.dao;

import vn.beemart.service.data.mssql.dto.Cart;

public interface CartDao {
    Cart getById(int cartId);

    Cart store(Cart cart);

    boolean checkValidCart(int cartId);
}
