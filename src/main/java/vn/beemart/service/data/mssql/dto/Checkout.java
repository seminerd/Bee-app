package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import vn.beemart.service.data.mssql.dto.enumeration.CheckoutStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "checkouts")
@Getter
@Setter
public class Checkout extends SqlDto implements Serializable {

    private static final long serialVersionUID = 3732977314303443303L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer checkoutId;
    @NotNull
    private Integer cartId;
    @Enumerated(EnumType.STRING)
    @NotNull
    private CheckoutStatus status;
    private Integer billingAddressId;
    private Integer shippingAddressId;
    private Integer paymentMethodId;
    @NotNull
    private Instant createdOn;
    private Instant completedOn;
    private Integer orderId;
}
