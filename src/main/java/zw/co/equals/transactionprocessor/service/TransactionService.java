package zw.co.equals.transactionprocessor.service;

import zw.co.equals.transactionprocessor.dto.TransactionDto;

public interface TransactionService {
    TransactionDto save(TransactionDto transactionDto);
}
