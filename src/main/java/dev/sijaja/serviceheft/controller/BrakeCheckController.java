package dev.sijaja.serviceheft.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sijaja.serviceheft.model.BrakeCheck;
import dev.sijaja.serviceheft.service.BrakeCheckService;

@RestController
@RequestMapping("/api/BrakeCheck")
@CrossOrigin
public class BrakeCheckController {
    private final BrakeCheckService service;
    public BrakeCheckController(BrakeCheckService service) { this.service = service; }

    @GetMapping
    public List<BrakeCheck> getAll() { return service.getAll(); }

    @PostMapping
    public BrakeCheck create(@RequestBody BrakeCheck c) { return service.save(c); }

    @GetMapping("/{id}")
    public BrakeCheck get(@PathVariable Integer id) { return service.get(id).orElseThrow(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}
