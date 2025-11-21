package dev.sijaja.serviceheft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class AuthViewController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("OWNER")) {
            return "redirect:/car-selection.html";
        } else if (request.isUserInRole("WORKSHOP")) {
            return "redirect:/workshop/wsdashboard.html";
        }
        return new String();
    }
    
}
