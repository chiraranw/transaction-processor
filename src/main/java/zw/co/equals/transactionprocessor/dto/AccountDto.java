package zw.co.equals.transactionprocessor.dto;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto extends BaseDto{
    private String accountNumber;
    private String type;
    private String status;
    private String description;
    private BigDecimal currentBalance;
    private UserDto user;
}