package zw.co.equals.transactionprocessor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import zw.co.equals.transactionprocessor.service.RedisService;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Optional;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, BigDecimal> redisTemplate;

    @Value("${redis.cache-retention-time}")
    private  int retentionTime;

    public RedisServiceImpl(RedisTemplate<String, BigDecimal> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Optional<BigDecimal> getBalance(String accountNumber) {
        log.info("Getting current balance for account: {}, from redis.", accountNumber);
        BigDecimal amount = redisTemplate.opsForValue().get(accountNumber);
        if (amount != null) {
            log.info("Found current balance for account: {}, from redis. Bal is: {}", accountNumber, amount);
            return Optional.of(amount);
        }
        return Optional.empty();
    }

    @Override
    public void setBalance(String accountNumber, BigDecimal amount) {
        log.info("Caching the current balance for account: {}", accountNumber);
        redisTemplate.opsForValue().set(accountNumber, amount, Duration.ofMinutes(retentionTime));
    }
}
