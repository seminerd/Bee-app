package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;
import vn.beemart.service.data.mssql.dto.enumeration.CartStatus;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart extends SqlDto implements Serializable {

    private static final long serialVersionUID = 7192963085647246029L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer cartId;
    @NotNull
    private String cartCode;
    @NotNull
    private Integer accountId;
    @Enumerated(EnumType.STRING)
    @NotNull
    private CartStatus status;
    @Min(0)
    private Integer point;
    @NotNull
    @Min(0)
    private BigDecimal totalAmount;
    @NotNull
    private Instant createdOn;
    private Instant completedOn;
}
