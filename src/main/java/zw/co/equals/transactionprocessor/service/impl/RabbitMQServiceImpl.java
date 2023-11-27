package zw.co.equals.transactionprocessor.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import zw.co.equals.transactionprocessor.config.RabbitMqProperties;
import zw.co.equals.transactionprocessor.service.RabbitMQService;

@Slf4j
@Service
public class RabbitMQServiceImpl implements RabbitMQService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    private final RabbitMqProperties rabbitMqProperties;

    public RabbitMQServiceImpl(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, RabbitMqProperties rabbitMqProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.rabbitMqProperties = rabbitMqProperties;
    }


    @Override
    public void retryPayment(Object payload) {
        log.info("Publishing to queue with key: {}", rabbitMqProperties.getPaymentsRetryQueueRoutingKey());
        try {
            rabbitTemplate.convertAndSend(
                    rabbitMqProperties.getExchange(),
                    rabbitMqProperties.getPaymentsRetryQueueRoutingKey(), objectMapper.writeValueAsString(payload));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
