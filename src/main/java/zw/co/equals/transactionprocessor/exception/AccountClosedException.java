package zw.co.equals.transactionprocessor.exception;

public class AccountClosedException extends RuntimeException{
    public AccountClosedException(String message) {
        super(message);
    }
}
