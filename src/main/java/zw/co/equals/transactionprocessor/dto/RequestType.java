package zw.co.equals.transactionprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Getter
public enum RequestType {
    CREDIT("CREDIT"),
    DEBIT("DEBIT");
    private String type;
}
