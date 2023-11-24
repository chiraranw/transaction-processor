package zw.co.equals.transactionprocessor.service;

import zw.co.equals.transactionprocessor.dto.AccountDto;
import zw.co.equals.transactionprocessor.dto.BalInquiryResponse;
import zw.co.equals.transactionprocessor.dto.Request;

import java.math.BigDecimal;

public interface AccountService {

    AccountDto credit(Request request);
    AccountDto debit(Request request);
    BalInquiryResponse balance(String accountNumber);

}
