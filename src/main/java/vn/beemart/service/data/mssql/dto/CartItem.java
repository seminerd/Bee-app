package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
public class CartItem extends SqlDto implements Serializable {

    private static final long serialVersionUID = -5018518095303481160L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer cartItemId;
    @NotNull
    private Integer productId;
    private Integer variantId;
    @NotNull
    private Integer cartId;
    @NotNull
    @Length(max = 500)
    private String productName;
    @NotNull
    @Length(max = 500)
    private String variantName;
    @NotNull
    @Length(max = 500)
    private String productImage;
    @NotNull
    @Min(0)
    private BigDecimal price;
    private BigDecimal comparePrice;
    @NotNull
    @Min(0)
    private Integer quantity;
}
