package dev.sijaja.serviceheft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class AuthViewController {

    @GetMapping("/login")
    public String login(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
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
