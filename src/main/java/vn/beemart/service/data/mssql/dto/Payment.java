package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment extends SqlDto implements Serializable {

    private static final long serialVersionUID = -8856096068203132873L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int paymentId;
    @NotNull
    private int paymentMethodId;
    @NotNull
    private Instant paidOn;
    @Min(0)
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String reference;
}
