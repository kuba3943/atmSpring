package pl.kuba.atm.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kuba.atm.databaseEntities.Account;
import pl.kuba.atm.databaseEntities.Bank;
import pl.kuba.atm.databaseEntities.Transaction;
import pl.kuba.atm.databaseEntities.User;
import pl.kuba.atm.services.AccountService;
import pl.kuba.atm.services.BankService;
import pl.kuba.atm.services.TransactionService;
import pl.kuba.atm.services.UserService;

import java.nio.channels.AcceptPendingException;
import java.time.LocalDate;
import java.util.List;

@Log
@Controller
@AllArgsConstructor
public class FrontController {

    private final UserService userService;
    private final BankService bankService;
    private final AccountService accountService;

    @GetMapping("/")
    public String hello(@AuthenticationPrincipal UserDetails user, Model model) {

        User aUser = userService.findUserByUsername(user.getUsername());
        Bank bank = new Bank("JD-Bank");
        bankService.createBank(bank);
        model.addAttribute("user", aUser);
        model.addAttribute("bank", bank);
        return "index";
    }

    @PostMapping("/abc")
    public String abc(@AuthenticationPrincipal UserDetails user, Model model) {

        String username = user.getUsername();
        model.addAttribute("username", username);
        model.addAttribute("user", user);
        return "abc";
    }

    @RequestMapping("/login")
    public String login() {

        return "login";
    }

    @RequestMapping("/login_error")
    public String login_error() {
        return "login_error";
    }

    @GetMapping("/account")
    public String getAccounts(@AuthenticationPrincipal UserDetails user, Model model) {
        User aUser = userService.findUserByUsername(user.getUsername());

        List<Account> accountList = aUser.getAccounts();
        model.addAttribute("choosenAccount", new Account());
        model.addAttribute("accounts", accountList);
        model.addAttribute("user", aUser);

        return "account";
    }


    @PostMapping("/account")
    public String chooseAction(@ModelAttribute Account choosenAccount, Model model) {

        Account account = accountService.findAccountByUuid(choosenAccount.getUuid());

        Double balance = accountService.getBalance(account.getUuid());

        model.addAttribute("account", account);
        model.addAttribute("balance", balance);
        return "accountDetails";

    }

    @PostMapping("/accountDetails")
    public String showHistory(@ModelAttribute Account account, Model model, @RequestParam(value = "action", required = false) String action) {

        Account accountHistory = accountService.findAccountByUuid(account.getUuid());

        Double balance = accountService.getBalance(accountHistory.getUuid());
        model.addAttribute("account", accountHistory);
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("balance", balance);
        model.addAttribute("data", LocalDate.now());

        if (action.equals("history")) {
            return "history";
        } else if (action.equals("withdraw")) {
            return "withdraw";
        } else {
            return "accountDetails";
        }

    }

    @PostMapping("/withdraw")
    public String withdrawMoney(@ModelAttribute Transaction transaction, Model model, @RequestParam(value = "uuid", required = false) String uuid) {

        accountService.addTransaction(-1*transaction.getAmount(), transaction.getMemo(), transaction.getTimestamp(), uuid);
        Account account = accountService.findAccountByUuid(uuid);

        Double balance = accountService.getBalance(account.getUuid());

        model.addAttribute("account", account);
        model.addAttribute("balance", balance);
            return "accountDetails";


    }



}
