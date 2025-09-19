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

import dev.sijaja.serviceheft.model.ElectricCheck;
import dev.sijaja.serviceheft.service.ElectricCheckService;

@RestController
@RequestMapping("/api/ElectricCheck")
@CrossOrigin
public class ElectricCheckController {
    private final ElectricCheckService service;
    public ElectricCheckController(ElectricCheckService service) { this.service = service; }

    @GetMapping
    public List<ElectricCheck> getAll() { return service.getAll(); }

    @PostMapping
    public ElectricCheck create(@RequestBody ElectricCheck c) { return service.save(c); }

    @GetMapping("/{id}")
    public ElectricCheck get(@PathVariable Integer id) { return service.get(id).orElseThrow(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}
