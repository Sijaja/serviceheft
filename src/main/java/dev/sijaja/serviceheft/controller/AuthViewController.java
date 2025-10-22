package dev.sijaja.serviceheft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthViewController {
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
