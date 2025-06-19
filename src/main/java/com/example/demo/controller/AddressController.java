package com.example.demo.Controller;

import java.util.List;
import com.example.demo.Entity.*;
import com.example.demo.Repository.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.demo.Exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/person/address")
public class AddressController {
    private final AddressRepository addressRepo;
    private final PersonRepository personRepo;

    public AddressController(AddressRepository addressRepo, PersonRepository personRepo){
    this.addressRepo = addressRepo;
    this.personRepo = personRepo;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addAddress(@PathVariable Long id, @RequestBody Address address) {
        Person person = personRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found"));
    
        address.setPerson(person);
        return ResponseEntity.ok(addressRepo.save(address));
    }

    @GetMapping("/{id}")
    public List<Address> getAddress(@PathVariable Long id) {
        Person person = personRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found"));
        List<Address> address = person.getAddress(); 
        return address;
    }

    @GetMapping("/{id}/{addressId}")
    public ResponseEntity<?> getAddress(@PathVariable Long id, @PathVariable Long addressId) {
        Person person = personRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found"));

        return person.getAddress().stream()
        .filter(a -> a.getId().equals(addressId))
        .findFirst()
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        
    }
    
    

}
