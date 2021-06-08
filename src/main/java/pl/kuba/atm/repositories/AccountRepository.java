package pl.kuba.atm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.atm.databaseEntities.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}
