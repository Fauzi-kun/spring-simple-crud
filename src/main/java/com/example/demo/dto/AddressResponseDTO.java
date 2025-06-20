package com.example.demo.dto;

public class AddressResponseDTO {
    private Long id;
    private String address;

    public AddressResponseDTO (Long id, String address){
        this.id = id;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
}
