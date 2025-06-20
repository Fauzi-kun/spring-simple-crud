package com.example.demo.entity;

import java.util.List;
import jakarta.persistence.*;

@Entity
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Address> address;

    public Person(){}

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
    public void setAddress(List<Address> address) {
        this.address = address;
    }
    public List<Address> getAddress() {
        return address;
    }

    // Password
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

}


