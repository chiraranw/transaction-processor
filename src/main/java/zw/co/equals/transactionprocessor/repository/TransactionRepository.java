package zw.co.equals.transactionprocessor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.equals.transactionprocessor.model.Transaction;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {
}
