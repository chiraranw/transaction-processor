package zw.co.equals.transactionprocessor.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.equals.transactionprocessor.dto.BalInquiryResponse;
import zw.co.equals.transactionprocessor.dto.Request;
import zw.co.equals.transactionprocessor.service.AccountService;

@RestController
@RequestMapping("/api/v1.0/transaction")
public class TransactionApi {

    private final AccountService accountService;

    public TransactionApi(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/credit")
    public ResponseEntity<?> credit(@RequestBody Request request) {
        return new ResponseEntity<>(accountService.credit(request), HttpStatus.CREATED);
    }

    @GetMapping("/bal/{accountNumber}")
    public ResponseEntity<BalInquiryResponse> balInquiry(@PathVariable("accountNumber") String accountNumber) {
        return new ResponseEntity<>(accountService.balance(accountNumber), HttpStatus.OK);
    }


}
