package zw.co.equals.transactionprocessor.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import zw.co.equals.transactionprocessor.dto.RequestType;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Transaction  extends BaseEntity{
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    RequestType requestType;
    BigDecimal amount;
}
