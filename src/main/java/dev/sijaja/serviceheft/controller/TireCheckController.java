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

import dev.sijaja.serviceheft.model.TireCheck;
import dev.sijaja.serviceheft.service.TireCheckService;

@RestController
@RequestMapping("/api/TireCheck")
@CrossOrigin
public class TireCheckController {
    private final TireCheckService service;
    public TireCheckController(TireCheckService service) { this.service = service; }

    @GetMapping
    public List<TireCheck> getAll() { return service.getAll(); }

    @PostMapping
    public TireCheck create(@RequestBody TireCheck c) { return service.save(c); }

    @GetMapping("/{id}")
    public TireCheck get(@PathVariable Integer id) { return service.get(id).orElseThrow(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}
