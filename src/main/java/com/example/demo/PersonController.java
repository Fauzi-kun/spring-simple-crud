package com.example.demo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/person")
public class PersonController {
    
    private PersonRepository repository;

    public PersonController(PersonRepository repository){
        this.repository = repository;
    }
    
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
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
    public Person updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        Person person = repository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
        person.setName(personDetails.getName());
        return repository.save(person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        repository.deleteById(id);
    }
    
    
    
}
