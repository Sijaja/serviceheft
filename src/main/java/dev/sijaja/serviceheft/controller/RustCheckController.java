package dev.sijaja.serviceheft.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sijaja.serviceheft.service.RustCheckService;

@RestController
@RequestMapping("/api/RustCheck")
@CrossOrigin
public class RustCheckController {
    private final RustCheckService service;
    public RustCheckController(RustCheckService service) { this.service = service; }

}
