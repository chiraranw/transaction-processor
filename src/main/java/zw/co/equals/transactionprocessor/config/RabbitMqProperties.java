package zw.co.equals.transactionprocessor.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class RabbitMqProperties {

    @Value("${rabbitmq.host}")
    private String host;
    @Value("${rabbitmq.port}")
    private int port;
    @Value("${rabbitmq.username}")
    private String username;
    @Value("${rabbitmq.password}")
    private String password;
    @Value("${rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${zw.co.equals.banking.payments-queue}")
    private String paymentsInQueue;
    @Value("${zw.co.equals.banking.payments-routing-key}")
    private String paymentsInQueueRoutingKey;


    @Value("${zw.co.equals.banking.payments-retry-queue}")
    private String paymentsRetryQueue;
    @Value("${zw.co.equals.banking.payments-retry-routing-key}")
    private String paymentsRetryQueueRoutingKey;

    @Value("${zw.co.equals.banking.exchange}")
    private String exchange;
    @Value("${zw.co.equals.banking.message-ttl}")
    private int messageTTL;
}
