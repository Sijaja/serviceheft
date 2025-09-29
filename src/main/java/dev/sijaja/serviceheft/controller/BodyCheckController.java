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

import dev.sijaja.serviceheft.model.BodyCheck;
import dev.sijaja.serviceheft.service.BodyCheckService;

@RestController
@RequestMapping("/api/BodyCheck")
@CrossOrigin
public class BodyCheckController {
    private final BodyCheckService service;
    public BodyCheckController(BodyCheckService service) { this.service = service; }

    @GetMapping
    public List<BodyCheck> getAll() { return service.getAll(); }

    @PostMapping
    public BodyCheck create(@RequestBody BodyCheck c) { return service.save(c); }

    @GetMapping("/{id}")
    public BodyCheck get(@PathVariable Integer id) { return service.get(id).orElseThrow(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}
