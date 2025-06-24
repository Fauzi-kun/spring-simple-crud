package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.*;
import com.example.demo.service.PersonService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/person")
public class PersonController {
    
    private final PersonService service;
    
    @PostMapping
    public ResponseEntity<?> createPerson(@Valid @RequestBody PersonRequestDTO dto) {
            return ResponseEntity.status(201).body(service.createPerson(dto)); 
    }

    @GetMapping
    public ResponseEntity<?> getAllPersons() {  
        return ResponseEntity.ok(service.getAllPerson());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Long id) {  
        return ResponseEntity.ok(service.getByIdPerson(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Long id, @Valid @RequestBody PersonRequestDTO dto) {
        return ResponseEntity.ok(service.updatePerson(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id){
        service.deletePerson(id);
        return ResponseEntity.status(204).body("Person deleted");
    }
}
