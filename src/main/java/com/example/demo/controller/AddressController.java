package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.AddressRequestDTO;
import com.example.demo.dto.AddressResponseDTO;
import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<?> addAddress(@PathVariable Long id, @RequestBody AddressRequestDTO dto) {
        Person person = personRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Id not found"));

        Address address = new Address();
        address.setAddress(dto.getAddress());
        address.setPerson(person);

        Address saved = addressRepo.save(address);
        AddressResponseDTO response = new AddressResponseDTO(saved.getId(), saved.getAddress());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public List<AddressResponseDTO> getAddress(@PathVariable Long id) {
        Person person = personRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found"));
        List<AddressResponseDTO> response = person.getAddress().stream().map(address -> new AddressResponseDTO(address.getId(), address.getAddress()))
        .collect(Collectors.toList());
        return response;
    }

    @GetMapping("/{id}/{addressId}")
    public ResponseEntity<?> getAddressDetail(@PathVariable Long id, @PathVariable Long addressId) {
        Person person = personRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id Person not found"));

        Address address = person.getAddress().stream()
        .filter(a -> a.getId().equals(addressId))
        .findFirst().orElseThrow(() -> new ResourceNotFoundException("Id address not found"));
        AddressResponseDTO response = new AddressResponseDTO(address.getId(), address.getAddress());
        return ResponseEntity.ok(response);
    }
    
    

}
