package com.example.demo.Entity;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "nama tidak boleh kosong")
    @Size(min = 2, max = 50, message = "nama harus antara 2 - 50 karakter")
    private String name;
    private String email;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<PersonAddress> address;

    // Getter Setter
    // Id
    public Long getId() {
        return id;
    }

    // Name
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    // Email
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    // Address
    public void setAddress(List<PersonAddress> address) {
        this.address = address;
    }
    public List<PersonAddress> getAddress() {
        return address;
    }

}


