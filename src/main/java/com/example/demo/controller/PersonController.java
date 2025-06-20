package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.*;
import com.example.demo.entity.Person;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PersonRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    
    private final PersonRepository repository;

    public PersonController(PersonRepository repository){
        this.repository = repository;
    }
    
    @PostMapping
    public ResponseEntity<?> createPerson(@Valid @RequestBody PersonRequestDTO dto) {
        try {
            Person person = new Person();
            person.setName(dto.getName());
            person.setEmail(dto.getEmail());
            person.setPassword(dto.getPassword());

            Person saved = repository.save(person);
            PersonResponseDTO response = PersonToDTO(saved);

            return ResponseEntity.ok(response); 
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Data invalid: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPersons() {       
        List<PersonResponseDTO> finded = repository.findAll().stream().map(this::PersonToDTO)
        .collect(Collectors.toList());
        
        return ResponseEntity.ok(finded);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Long id) {       
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        PersonResponseDTO response = PersonToDTO(person);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Long id, @Valid @RequestBody PersonRequestDTO dto) {
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        person.setName(dto.getName());
        person.setEmail(dto.getEmail());
        person.setPassword(dto.getPassword());
        Person saved = repository.save(person);

        PersonResponseDTO response = PersonToDTO(saved);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.ok().body("Person deleted");
    }
    
    private PersonResponseDTO PersonToDTO(Person person){
        List<AddressResponseDTO> address = person.getAddress().stream()
        .map(addr -> new AddressResponseDTO(addr.getId(), addr.getAddress()))
        .collect(Collectors.toList());

        return new PersonResponseDTO(person.getId(), person.getName(), person.getEmail(), address);
    }
    
    
}
