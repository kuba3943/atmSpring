package pl.kuba.atm.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kuba.atm.databaseEntities.Bank;
import pl.kuba.atm.repositories.BankRepository;

@Service
@AllArgsConstructor
public class BankService {

    private final BankRepository bankRepository;

    public Bank createBank(String name){
        Bank newBank = new Bank(name);
        return bankRepository.save(newBank);
    }
}
