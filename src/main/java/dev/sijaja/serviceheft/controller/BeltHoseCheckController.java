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

import dev.sijaja.serviceheft.model.BeltHoseCheck;
import dev.sijaja.serviceheft.service.BeltHoseCheckService;

@RestController
@RequestMapping("/api/BeltHoseCheck")
@CrossOrigin
public class BeltHoseCheckController {
    private final BeltHoseCheckService service;
    public BeltHoseCheckController(BeltHoseCheckService service) { this.service = service; }

    @GetMapping
    public List<BeltHoseCheck> getAll() { return service.getAll(); }

    @PostMapping
    public BeltHoseCheck create(@RequestBody BeltHoseCheck c) { return service.save(c); }

    @GetMapping("/{id}")
    public BeltHoseCheck get(@PathVariable Integer id) { return service.get(id).orElseThrow(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}
