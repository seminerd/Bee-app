package vn.beemart.service.data.mssql.dao.impl;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.CartDao;
import vn.beemart.service.data.mssql.dto.Cart;
import vn.beemart.service.data.mssql.repository.CartRepository;

@Service
public class CartDaoImpl implements CartDao {
    private final CartRepository cartRepository;

    public CartDaoImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart getById(int cartId) {
        val cart = cartRepository.findById(cartId);
        return cart.orElse(null);
    }

    @Override
    public Cart store(Cart cart) {
        return cartRepository.saveAndFlush(cart);
    }

    @Override
    public boolean checkValidCart(int cartId) {
        return cartRepository.existsById(cartId);
    }
}
