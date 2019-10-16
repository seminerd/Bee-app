package vn.beemart.service.web.rest.model.cart;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import vn.beemart.service.data.mssql.dto.enumeration.CartStatus;
import vn.beemart.service.web.rest.model.cartitem.CartItemResponse;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@JsonRootName(value = "cart")
public class CartResponse {
    private Integer cartId;
    private String cartCode;
    private Integer accountId;
    private List<CartItemResponse> items;
    @Enumerated(EnumType.STRING)
    private CartStatus status;
    private Integer point;
    private BigDecimal totalAmount;
    private Instant createdOn;
    private Instant completedOn;
}
