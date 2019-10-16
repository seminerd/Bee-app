package vn.beemart.service.data.mssql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.beemart.service.data.mssql.dto.CartItem;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findCartItemsByCartId(int cartId);
    CartItem findByCartIdAndAndProductIdAndVariantId(int cartId, int productId, int variantId);
}
