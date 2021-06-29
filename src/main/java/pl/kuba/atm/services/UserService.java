package pl.kuba.atm.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kuba.atm.databaseEntities.Account;
import pl.kuba.atm.databaseEntities.Bank;
import pl.kuba.atm.databaseEntities.Transaction;
import pl.kuba.atm.databaseEntities.User;
import pl.kuba.atm.repositories.AccountRepository;
import pl.kuba.atm.repositories.BankRepository;
import pl.kuba.atm.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final BankRepository bankRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found in database!"));
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username).orElseThrow();
    }

    public User addUser(User user, Bank bank){

        User userToSave = userRepository.save(user);

        bank.getUsers().add(userToSave);

        bankRepository.save(bank);

        return userToSave;
    }



    public User addAccountToUser(User user, Account account, Bank bank) {



        if (user.getAccounts()!=null){
            user.getAccounts().add(account);
        }else {
            user.setAccounts(new ArrayList<>());
            user.getAccounts().add(account);
        }
        accountRepository.save(account);
        userRepository.save(user);

        bank.getAccounts().add(account);

        bankRepository.save(bank);

        return user;

    }

    public List<Account> getAccounts(User user){
       return user.getAccounts();
    }

    public List<Transaction> getTransactionList ( String accId){
        Account account = accountRepository.findById(accId).orElseThrow();

        return account.getTransactions();

    }

    public User depositFunds(User user, String accId, double amount, String memo){
        user.getAccounts().stream().filter(a -> a.getUuid().equals(accId)).collect(Collectors.toList())
                .get(0).getTransactions().add(new Transaction(amount,memo));

        return userRepository.save(user);
    }

    public User withdrowFunds(User user, String accId, double amount, String memo){

        Account account = user.getAccounts().stream().filter(a -> a.getUuid().equals(accId)).collect(Collectors.toList())
                .get(0);

        if (accountService.getBalance(accId)<amount){
            return user;
        } else {

            account.getTransactions().add(new Transaction(-1 * amount, memo));
        }

        return userRepository.save(user);
    }


    public User transferFunds (User user, String fromAccId, String toAccId, double amount, String memo ){
        Account fromAccount = user.getAccounts().stream().filter(a -> a.getUuid().equals(fromAccId)).collect(Collectors.toList())
                .get(0);

        Account toAccount =user.getAccounts().stream().filter(a -> a.getUuid().equals(toAccId)).collect(Collectors.toList())
                .get(0);
        if (accountService.getBalance(fromAccId)<amount){
            return user;
        } else {

            fromAccount.getTransactions().add(new Transaction(-1 * amount, memo));
            toAccount.getTransactions().add(new Transaction(amount, memo));
        }

        return userRepository.save(user);


    }





}
