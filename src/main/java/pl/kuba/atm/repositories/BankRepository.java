package pl.kuba.atm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.atm.databaseEntities.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
