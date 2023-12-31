package zw.co.equals.transactionprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalInquiryResponse {
    private String accountNumber;
    private BigDecimal amount;
    private LocalDateTime date;
}
