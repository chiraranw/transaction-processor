package zw.co.equals.transactionprocessor.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class User extends BaseEntity{
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String mobile;
    private String email;
}
