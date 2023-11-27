package zw.co.equals.transactionprocessor.config;

import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.amqp.core.BindingBuilder.bind;

@Configuration
public class RabbitMqDeclarations {


    private final RabbitMqProperties rabbitMqProperties;

    public RabbitMqDeclarations(RabbitMqProperties rabbitMqProperties) {
        this.rabbitMqProperties = rabbitMqProperties;
    }


    @Bean
    Queue paymentsInQueue() {
        return new Queue(rabbitMqProperties.getPaymentsInQueue(), true, false, false);
    }

    @Bean
    Queue paymentsRetryQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", rabbitMqProperties.getMessageTTL());
        args.put("x-dead-letter-exchange", rabbitMqProperties.getExchange());
        args.put("x-dead-letter-routing-key", rabbitMqProperties.getPaymentsInQueueRoutingKey());
        return new Queue(rabbitMqProperties.getPaymentsRetryQueue(), true, false, false,args);
    }

    @Bean
    public Declarables directBindings() {
        Queue paymentsInQueue = this.paymentsInQueue();
        Queue paymentsRetryQueue = this.paymentsRetryQueue();
        DirectExchange paymentsExchange = new DirectExchange(rabbitMqProperties.getExchange());
        return new Declarables(
                paymentsExchange,
                bind(paymentsInQueue).to(paymentsExchange).with(rabbitMqProperties.getPaymentsInQueueRoutingKey()),
                bind(paymentsRetryQueue).to(paymentsExchange).with(rabbitMqProperties.getPaymentsRetryQueueRoutingKey()));
    }

}