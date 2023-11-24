package zw.co.equals.transactionprocessor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import zw.co.equals.transactionprocessor.dto.TransactionDto;
import zw.co.equals.transactionprocessor.model.Transaction;
import zw.co.equals.transactionprocessor.repository.TransactionRepository;
import zw.co.equals.transactionprocessor.service.TransactionService;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TransactionDto save(TransactionDto transactionDto) {
        log.info("Saving the transaction : {}", transactionDto);
        Transaction transaction = transactionRepository.save(
                modelMapper.map(transactionDto, Transaction.class));
        return modelMapper.map(transaction, TransactionDto.class);
    }
}
