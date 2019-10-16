package vn.beemart.service.web.rest.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import vn.beemart.service.common.controller.BaseController;
import vn.beemart.service.common.validate.NotFoundException;
import vn.beemart.service.common.validate.ProhibitiveException;
import vn.beemart.service.data.mssql.dao.CartDao;
import vn.beemart.service.data.mssql.dao.CartItemDao;
import vn.beemart.service.data.mssql.dto.Cart;
import vn.beemart.service.data.mssql.dto.CartItem;
import vn.beemart.service.data.mssql.dto.enumeration.CartStatus;
import vn.beemart.service.data.mssql.service.CartService;
import vn.beemart.service.data.mssql.dto.CartCode;
import vn.beemart.service.data.mssql.repository.CartCodeRepository;
import vn.beemart.service.web.rest.model.cart.CartResponse;
import vn.beemart.service.web.rest.model.cart.NewCartRequest;
import vn.beemart.service.web.rest.model.cartitem.CartItemRequest;
import vn.beemart.service.web.rest.model.cartitem.ChangeItemQuantityRequest;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

@RestController
@CommonsLog(topic = "services")
@RequestMapping("/cart")
public class CartController extends BaseController {
    private final CartDao cartDao;

    private final CartItemDao cartItemDao;

    private final CartService cartService;

    private final CartCodeRepository cartCodeRepository;

    public CartController(CartDao cartDao, CartItemDao cartItemDao, CartService cartService, CartCodeRepository cartCodeRepository) {
        this.cartDao = cartDao;
        this.cartItemDao = cartItemDao;
        this.cartService = cartService;
        this.cartCodeRepository = cartCodeRepository;
    }

    @ApiOperation(value = "Lấy thông tin giỏ hàng bằng Id giỏ hàng")
    @GetMapping("/{id}")
    public CartResponse getCartByCartId(@PathVariable("id") int cartId) {
        try {
            return cartService.getCartResponse(cartId);
        } catch (Exception e) {
            throw new NotFoundException("Không tồn tại giỏ hàng thỏa mãn");
        }
    }

    @ApiOperation(value = "Thêm sản phẩm mới vào giỏ hàng mới", notes = "Wrap json body bên dưới với json root {\"cart\":{model_below}}")
    @PostMapping
    public CartResponse addItemToCart(@RequestBody @Valid NewCartRequest model) {
        val newCart = new Cart();
        val newCartCode = new CartCode();
        cartCodeRepository.save(newCartCode);
        newCart.setCartCode(newCartCode.toString());
        newCart.setAccountId(0);
        newCart.setCreatedOn(new Date().toInstant());
        newCart.setPoint(0);
        newCart.setStatus(CartStatus.open);
        newCart.setTotalAmount(BigDecimal.ZERO);
        cartDao.store(newCart);
        createItemForCart(newCart.getCartId(), model.getCartItem());
        try {
            return cartService.getCartResponse(newCart.getCartId());
        } catch (Exception e) {
            throw new NotFoundException("Không tồn tại giỏ hàng thỏa mãn");
        }
    }

    @ApiOperation(value = "Thêm sản phẩm mới vào giỏ hàng đã tồn tại", notes = "Wrap json body bên dưới với json root {\"item\":{model_below}}")
    @PostMapping("/{id}/items")
    public CartResponse addItemToCart(@PathVariable(value = "id") int cartId,
                                      @RequestBody @Valid CartItemRequest model) {
        val cart = cartDao.getById(cartId);
        if(cart == null) {
            throw new NotFoundException("Không tồn tại giỏ hàng thỏa mãn");
        }
        if(cart.getStatus().equals(CartStatus.closed)) {
            throw new ProhibitiveException("Giỏ hàng đã được checkout, không thể thay đổi thông tin");
        }
        val item = cartItemDao.getByVariantIdAndProductIdAndCartId(cartId, model.getProductId(), model.getVariantId());
        if(item == null) {
            createItemForCart(cart.getCartId(), model);
        } else {
            int addedQuantity = item.getQuantity() + model.getQuantity();
            item.setQuantity(addedQuantity);
            cartItemDao.store(item);
        }
        try {
            return cartService.getCartResponse(cart.getCartId());
        } catch (Exception e) {
            throw new NotFoundException("Không tồn tại giỏ hàng thỏa mãn");
        }
    }

    @ApiOperation(value = "Thay đổi thông tin số lượng của sản phẩm trong giỏ hàng", notes = "Wrap json body bên dưới với json root {\"item\":{model_below}}")
    @PostMapping("/{id}/items/{item_id}")
    public CartResponse changeItemInCart(@PathVariable("id") int cartId,
                                         @PathVariable("item_id") int itemId,
                                         @RequestBody @Valid ChangeItemQuantityRequest model) {
        val cart = cartDao.getById(cartId);
        if(cart == null) {
            throw new NotFoundException("Không tồn tại giỏ hàng thỏa mãn");
        }
        if(cart.getStatus().equals(CartStatus.closed)) {
            throw new ProhibitiveException("Giỏ hàng đã được checkout, không thể thay đổi thông tin");
        }
        val cartItem = cartItemDao.getById(itemId);
        if(cartItem == null) {
            throw new NotFoundException("Không tồn tại sản phẩm thỏa mãn");
        }
        if(model.getQuantity() == 0) {
            cartItemDao.remove(cartItem);
        } else {
            cartItem.setQuantity(model.getQuantity());
            cartItemDao.store(cartItem);
        }
        try {
            return cartService.getCartResponse(cart.getCartId());
        } catch (Exception e) {
            throw new NotFoundException("Không tồn tại giỏ hàng thỏa mãn");
        }
    }

    private void createItemForCart(int cartId, CartItemRequest model) {
        val cartItem = new CartItem();
        cartItem.setCartId(cartId);
        // TODO: Tìm sản phẩm dựa vào model gửi lên và kiểm tra tính hợp lệ
        cartItem.setPrice(BigDecimal.ZERO);
        cartItem.setComparePrice(BigDecimal.ZERO);
        cartItem.setProductId(model.getProductId());
        cartItem.setProductName("Product");
        cartItem.setProductImage("Url");
        cartItem.setVariantId(model.getVariantId());
        cartItem.setVariantName("Name");
        cartItem.setQuantity(model.getQuantity());
        cartItemDao.store(cartItem);
    }
}
