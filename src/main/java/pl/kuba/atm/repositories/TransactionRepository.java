package pl.kuba.atm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.atm.databaseEntities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
