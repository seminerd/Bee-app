package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import vn.beemart.service.common.generator.StringPrefixedSequenceIdGenerator;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "seqbeecartcode")
public class CartCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    @GenericGenerator(
            name = "cart_seq",
            strategy = "vn.beemart.service.common.generator.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CART"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            }
    )
    private String cartCode;

    @Override
    public String toString() {
        return cartCode;
    }
}
