package zw.co.equals.transactionprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private String accountNumber;
    BigDecimal amount;
    RequestType requestType;
}
