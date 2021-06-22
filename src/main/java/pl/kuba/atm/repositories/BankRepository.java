package pl.kuba.atm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.atm.databaseEntities.Bank;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {

    @Override
    Optional<Bank> findById(Long aLong);
}
