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

import dev.sijaja.serviceheft.model.Workshop;
import dev.sijaja.serviceheft.service.WorkshopService;

@RestController
@RequestMapping("/api/workshops")
@CrossOrigin
public class WorkshopController {
    private final WorkshopService service;
    public WorkshopController(WorkshopService service) { this.service = service; }

    @GetMapping
    public List<Workshop> getAll() { return service.getAll(); }

    @PostMapping
    public Workshop create(@RequestBody Workshop ws) { return service.save(ws); }

    @GetMapping("/{id}")
    public Workshop get(@PathVariable Integer id) { return service.get(id).orElseThrow(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { service.delete(id); }
}
