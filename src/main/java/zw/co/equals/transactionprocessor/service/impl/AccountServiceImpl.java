package zw.co.equals.transactionprocessor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import zw.co.equals.transactionprocessor.dto.*;
import zw.co.equals.transactionprocessor.exception.AccountClosedException;
import zw.co.equals.transactionprocessor.exception.RecordNotFoundException;
import zw.co.equals.transactionprocessor.model.Account;
import zw.co.equals.transactionprocessor.model.AccountStatus;
import zw.co.equals.transactionprocessor.repository.AccountRepository;
import zw.co.equals.transactionprocessor.service.AccountService;
import zw.co.equals.transactionprocessor.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    private final TransactionService transactionService;

    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.transactionService = transactionService;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public AccountDto credit(Request request) {

        log.info("Processing request to credit the account: {}", request);
        Account account = findAccount(request.getAccountNumber());
        if (account.getStatus().name().equals(AccountStatus.CLOSED.name())) {
            log.error("Could not perform the transaction on a closed account :{}", account);
            throw new AccountClosedException("Specified account is closed, cannot receive funds: " + account.getAccountNumber());
        }

        account.setCurrentBalance(account.getCurrentBalance().add(request.getAmount()));

        Account newRecord = accountRepository.save(account);
        transactionService.save(TransactionDto.builder()
                .requestType(RequestType.CREDIT)
                .amount(request.getAmount())
                .accountNumber(request.getAccountNumber()).build());
        log.info("Credit transaction processed successfully: {}", newRecord);

        // TODO: 24/11/2023 Update Redis on a different thread
        // TODO: 24/11/2023 Push a notification  on a different thread

        return modelMapper.map(newRecord, AccountDto.class);
    }

    @Override
    public AccountDto debit(Request request) {
        return null;
    }

    @Override
    public BalInquiryResponse balance(String accountNumber) {
        log.info("Inquiring balance for account :{}", accountNumber);
        //TODO check in redis first
        Account account = findAccount(accountNumber);
        return BalInquiryResponse
                .builder()
                .accountNumber(accountNumber)
                .amount(account.getCurrentBalance())
                .date(LocalDateTime.now())
                .build();
    }


    public Account findAccount(String accountNumber) {
        return accountRepository
                .findAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new RecordNotFoundException("Could not find the specified account number :" + accountNumber));
    }
}
