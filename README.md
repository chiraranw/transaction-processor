# Transaction Processor Service

This service has two APIs.

#### 1. Balance Inquiry
Returns the current balance of the given account.
Caches the current balance for a configurable amount of time.

#### 2. Credit Account
Credits an account, sample payload is shown below: 

```json
{
    "accountNumber": "9140000328271",
    "type": "CREDIT",
    "amount": "1"
}
```

#### RabbitMQ Listener
Listens for credit/payment request and consumes from the ***IN.ZW.PAYMENTS***. When an error happens, the request
is retried using the rabbitMQ DL queue mechanism.

