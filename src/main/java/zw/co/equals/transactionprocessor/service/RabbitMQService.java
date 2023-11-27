package zw.co.equals.transactionprocessor.service;

public interface RabbitMQService {
    void retryPayment(Object payload);
}
