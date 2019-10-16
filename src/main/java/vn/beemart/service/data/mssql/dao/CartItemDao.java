package vn.beemart.service.data.mssql.dao;

import vn.beemart.service.data.mssql.dto.CartItem;

import java.util.List;

public interface CartItemDao {
    CartItem getById(int cartItemId);
    List<CartItem> getItemsByCartId(int cartId);
    CartItem getByVariantIdAndProductIdAndCartId(int cartId, int productId, int variantId);
    CartItem store(CartItem cartItem);
    void remove(CartItem cartItem);
}
