package vn.beemart.service.data.mssql.dto;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import vn.beemart.service.common.generator.StringPrefixedSequenceIdGenerator;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "seqbeeordercode")
public class OrderCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @GenericGenerator(
            name = "order_seq",
            strategy = "vn.beemart.service.common.generator.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "APP"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            }
    )
    private String orderCode;

    @Override
    public String toString() {
        return orderCode;
    }
}
