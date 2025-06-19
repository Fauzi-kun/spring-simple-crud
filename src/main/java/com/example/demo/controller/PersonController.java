package com.example.demo.Controller;

import java.util.List;
import com.example.demo.Entity.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Repository.PersonRepository;
import com.example.demo.Exception.ResourceNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    
    private PersonRepository repository;

    public PersonController(PersonRepository repository){
        this.repository = repository;
    }
    
    @PostMapping
    public Person createPerson(@Valid @RequestBody Person person) {
        return repository.save(person);
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPersonById(@PathVariable Long id) {

        return repository.findById(id)
        .<ResponseEntity<Object>>map(user -> ResponseEntity.ok(user))
        .orElseGet(() -> ResponseEntity.status(404).body("User tidak ditemukan"));
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @Valid @RequestBody Person personDetails) {
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        person.setName(personDetails.getName());
        return repository.save(person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        repository.deleteById(id);
    }
    
    
    
}
