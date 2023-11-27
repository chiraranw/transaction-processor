package zw.co.equals.transactionprocessor.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import zw.co.equals.transactionprocessor.dto.Request;
import zw.co.equals.transactionprocessor.exception.RecordNotFoundException;
import zw.co.equals.transactionprocessor.service.AccountService;
import zw.co.equals.transactionprocessor.service.RabbitMQService;

import java.io.IOException;

@Slf4j
@EnableRabbit
@Configuration
public class PaymentListener {

    private final ObjectMapper objectMapper;
    private final AccountService accountService;

    private final RabbitMQService rabbitMQService;

    public PaymentListener(ObjectMapper objectMapper, AccountService accountService, RabbitMQService rabbitMQService) {
        this.objectMapper = objectMapper;
        this.accountService = accountService;
        this.rabbitMQService = rabbitMQService;
    }


    @SneakyThrows
    @SuppressWarnings("java:S112")
    @RabbitListener(queues = "${zw.co.equals.banking.payments-queue}")
    public void paymentConsumer(Message message) {
        try {
            String payload = objectMapper.readValue(message.getBody(), String.class);
            Request request = objectMapper.readValue(payload, Request.class);
            System.out.println(payload.replace("\\", ""));
            log.info("Consuming message from queue: {}", objectMapper.writeValueAsString(payload));
            accountService.credit(request);
        } catch (RecordNotFoundException ex) {
            String payload = objectMapper.readValue(message.getBody(), String.class);
            rabbitMQService.retryPayment(objectMapper.readValue(payload, Request.class));
        } catch (IOException e) {
            log.error("");
            throw new RuntimeException(e);
        }
    }
}
