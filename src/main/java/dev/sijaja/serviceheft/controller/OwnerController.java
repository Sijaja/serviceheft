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

import dev.sijaja.serviceheft.dto.OwnerSignupDto;
import dev.sijaja.serviceheft.model.Owner;
import dev.sijaja.serviceheft.service.OwnerService;

@RestController
@RequestMapping("/api/owners")
@CrossOrigin
public class OwnerController {

    private final OwnerService service;

    public OwnerController(OwnerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Owner> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Owner create(@RequestBody OwnerSignupDto dto) {
        return service.registerOwner(dto);
    }

    @GetMapping("/{id}")
    public Owner get(@PathVariable Integer id) {
        return service.get(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
    
}
