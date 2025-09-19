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

import dev.sijaja.serviceheft.model.HvacCheck;
import dev.sijaja.serviceheft.service.HvacCheckService;

@RestController
@RequestMapping("/api/HvacCheck")
@CrossOrigin
public class HvacCheckController {
    private final HvacCheckService service;
    public HvacCheckController(HvacCheckService service) { this.service = service; }

    @GetMapping
    public List<HvacCheck> getAll() { return service.getAll(); }

    @PostMapping
    public HvacCheck create(@RequestBody HvacCheck c) { return service.save(c); }

    @GetMapping("/{id}")
    public HvacCheck get(@PathVariable Integer id) { return service.get(id).orElseThrow(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}
