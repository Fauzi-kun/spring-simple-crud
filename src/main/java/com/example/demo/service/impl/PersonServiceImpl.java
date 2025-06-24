package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.example.demo.dto.*;
import com.example.demo.entity.Person;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository){
        this.repository = repository;
    }

    @Override
    public PersonResponseDTO createPerson(PersonRequestDTO dto){
        Person person = new Person();
        person.setName(dto.getName());
        person.setEmail(dto.getEmail());
        person.setPassword(dto.getPassword());

        Person saved = repository.save(person);
        return personToDto(saved);
    }

    @Override
    public List<PersonResponseDTO> getAllPerson(){
        List<PersonResponseDTO> persons = repository
        .findAll().stream().map(this::personToDto)
        .collect(Collectors.toList());
        
        return persons;
    }

    @Override
    public PersonResponseDTO getByIdPerson(Long id){
        Person person = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        return personToDto(person);
    }

    @Override
    public PersonResponseDTO updatePerson(Long id, PersonRequestDTO dto){
        Person person = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        person.setName(dto.getName());
        person.setEmail(dto.getEmail());
        person.setPassword(dto.getPassword());
        Person saved = repository.save(person);

        return personToDto(saved);
    }

    @Override
    public void deletePerson(Long id){
        repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        repository.deleteById(id);
    }
    
    private PersonResponseDTO personToDto(Person person){
        List<AddressResponseDTO> address = person
        .getAddress().stream().map(adr -> new AddressResponseDTO(adr.getId(), adr.getAddress()))
        .collect(Collectors.toList());

        return new PersonResponseDTO(person.getId(), person.getName(), person.getEmail(), address);
    }
}
