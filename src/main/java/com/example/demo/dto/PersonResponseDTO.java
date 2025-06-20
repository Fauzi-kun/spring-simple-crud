package com.example.demo.dto;

import java.util.List;

public class PersonResponseDTO {
    private Long id;
    private String name;
    private String email;
    private List<AddressResponseDTO> address;

    public PersonResponseDTO(Long id, String name, String email, List<AddressResponseDTO> address){
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<AddressResponseDTO> getAddress() {
        return address;
    }
}
