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

import dev.sijaja.serviceheft.model.RustCheck;
import dev.sijaja.serviceheft.service.RustCheckService;

@RestController
@RequestMapping("/api/RustCheck")
@CrossOrigin
public class RustCheckController {
    private final RustCheckService service;
    public RustCheckController(RustCheckService service) { this.service = service; }

    @GetMapping
    public List<RustCheck> getAll() { return service.getAll(); }

    @PostMapping
    public RustCheck create(@RequestBody RustCheck c) { return service.save(c); }

    @GetMapping("/{id}")
    public RustCheck get(@PathVariable Integer id) { return service.get(id).orElseThrow(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}
