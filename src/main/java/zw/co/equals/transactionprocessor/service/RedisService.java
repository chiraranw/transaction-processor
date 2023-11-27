package zw.co.equals.transactionprocessor.service;

import java.math.BigDecimal;
import java.util.Optional;

public interface RedisService {
    Optional<BigDecimal> getBalance(String accountNumber);
    void setBalance(String accountNumber, BigDecimal amount);
}
