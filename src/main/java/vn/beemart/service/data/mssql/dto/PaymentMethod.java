package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Entity
@Setter
@Table(name = "payment_methods")
public class PaymentMethod extends SqlDto implements Serializable {

    private static final long serialVersionUID = 6626117342235357952L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int paymentMethodId;
    @NotNull
    @Length(max = 50)
    private String name;
    @NotNull
    @Max(25)
    private String type;
    @NotNull
    private boolean integrated;
}
