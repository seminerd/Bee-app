package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import vn.beemart.service.common.generator.StringPrefixedSequenceIdGenerator;
import vn.beemart.service.data.mssql.dto.enumeration.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends SqlDto implements Serializable {

    private static final long serialVersionUID = -3147522704087439513L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int orderId;
    @NotNull
    private String orderCode;
    @NotNull
    private int cartId;
    @NotNull
    private int accountId;
    @NotNull
    private int checkoutId;
    @NotNull
    private int locationId;
    @Enumerated(EnumType.STRING)
    @NotNull
    private OrderStatus status;
    @Min(0)
    private BigDecimal orderDiscount;
    @Min(0)
    private BigDecimal deliveryFee;
    @NotNull
    private int expectedPaymentMethodId;
    private String os;
    private String device;
    private String note;
    @NotNull
    private Instant createdOn;
    private Instant goodsIssueOn;
    private Instant modifiedOn;
    private Instant shippedOn;
    @NotNull
    private boolean hasSyncWithPos;
}
