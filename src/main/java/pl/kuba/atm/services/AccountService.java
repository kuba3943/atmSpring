package pl.kuba.atm.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kuba.atm.databaseEntities.Account;
import pl.kuba.atm.databaseEntities.Transaction;
import pl.kuba.atm.repositories.AccountRepository;
import pl.kuba.atm.repositories.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public double getBalance(String id){
        double balance = 0;
        List<Transaction> transactionList = accountRepository.findById(id).orElseThrow().getTransactions();
        for (Transaction t: transactionList) {
            balance += t.getAmount();
        }
        return balance;
    }

    public Account addTransaction (double amount, String memo, LocalDate date, String accId){
        Transaction newTrans = new Transaction(amount,memo);
        Account account = accountRepository.findById(accId).orElseThrow();

        transactionRepository.save(newTrans);
        account.getTransactions().add(newTrans);
        accountRepository.save(account);
        return account;
    }

    public Account addAccount (Account account){
        return accountRepository.save(account);
    }


    public Account findAccountByUuid (String uuid){
        return accountRepository.findById(uuid).orElseThrow();
    }

}
