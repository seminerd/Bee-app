package vn.beemart.service.data.mssql.dao.impl;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.CartItemDao;
import vn.beemart.service.data.mssql.dto.CartItem;
import vn.beemart.service.data.mssql.repository.CartItemRepository;

import java.util.List;

@Service
public class CartItemDaoImpl implements CartItemDao {
    private final CartItemRepository cartItemRepository;

    public CartItemDaoImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItem getById(int cartItemId) {
        return cartItemRepository.findById(cartItemId).orElse(null);
    }

    @Override
    public List<CartItem> getItemsByCartId(int cartId) {
        return cartItemRepository.findCartItemsByCartId(cartId);
    }

    @Override
    public CartItem getByVariantIdAndProductIdAndCartId(int cartId, int productId, int variantId) {
        return cartItemRepository.findByCartIdAndAndProductIdAndVariantId(cartId, productId, variantId);
    }

    @Override
    public CartItem store(CartItem cartItem) {
        return cartItemRepository.saveAndFlush(cartItem);
    }

    @Override
    public void remove(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }
}
