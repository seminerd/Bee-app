package vn.beemart.service.data.mssql.service;

import lombok.val;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.beemart.service.data.mssql.dao.CartDao;
import vn.beemart.service.data.mssql.dao.CartItemDao;
import vn.beemart.service.data.mssql.dto.Cart;
import vn.beemart.service.data.mssql.dto.CartItem;
import vn.beemart.service.web.rest.model.cart.CartResponse;
import vn.beemart.service.web.rest.model.cartitem.CartItemResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final MapperFacade mapperFacade;

    private final CartDao cartDao;

    private final CartItemDao cartItemDao;

    public CartService(MapperFacade mapperFacade, CartDao cartDao, CartItemDao cartItemDao) {
        this.mapperFacade = mapperFacade;
        this.cartDao = cartDao;
        this.cartItemDao = cartItemDao;
    }

    public CartResponse getCartResponse(Integer cartId) throws Exception {
        if(cartId == null || !cartDao.checkValidCart(cartId)) {
            throw new Exception("Cart "+cartId+" không tồn tại");
        }
        val cart = cartDao.getById(cartId);
        val response = mapperFacade.map(cart, CartResponse.class);
        val items = cartItemDao.getItemsByCartId(cart.getCartId()).stream()
                .map(value -> mapperFacade.map(value, CartItemResponse.class))
                .collect(Collectors.toList());
        response.setItems(items);
        response.setCartCode(cart.getCartCode().toString());
        return response;
    }
}
