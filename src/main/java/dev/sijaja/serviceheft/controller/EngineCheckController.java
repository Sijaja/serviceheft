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

import dev.sijaja.serviceheft.model.EngineCheck;
import dev.sijaja.serviceheft.service.EngineCheckService;

@RestController
@RequestMapping("/api/EngineCheck")
@CrossOrigin
public class EngineCheckController {
    private final EngineCheckService service;
    public EngineCheckController(EngineCheckService service) { this.service = service; }

    @GetMapping
    public List<EngineCheck> getAll() { return service.getAll(); }

    @PostMapping
    public EngineCheck create(@RequestBody EngineCheck c) { return service.save(c); }

    @GetMapping("/{id}")
    public EngineCheck get(@PathVariable Integer id) { return service.get(id).orElseThrow(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}
