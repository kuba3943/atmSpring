package pl.kuba.atm.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kuba.atm.services.BankService;
import pl.kuba.atm.services.UserService;

@Controller
@AllArgsConstructor
public class FrontController {

    private final UserService userService;
    private final BankService bankService;

    @GetMapping("/")
    public String hello(@AuthenticationPrincipal UserDetails user,Model model) {


        model.addAttribute("user", user);
        return "index";
    }

    @PostMapping("/abc")
    public String abc(@AuthenticationPrincipal UserDetails user,Model model) {

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
}
