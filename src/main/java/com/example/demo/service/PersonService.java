package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PersonRequestDTO;
import com.example.demo.dto.PersonResponseDTO;

public interface PersonService {
    PersonResponseDTO createPerson(PersonRequestDTO dto);
    List<PersonResponseDTO> getAllPerson();
    PersonResponseDTO getByIdPerson(Long id);
    PersonResponseDTO updatePerson(Long id, PersonRequestDTO dto);
    void deletePerson(Long id);
}
