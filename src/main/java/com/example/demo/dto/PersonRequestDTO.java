package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PersonRequestDTO {

    @NotBlank(message = "nama tidak boleh kosong")
    @Size(min = 2, max = 50, message = "nama harus antara 2 - 50 karakter")
    private String name;

    @Email
    private String email;

    @NotBlank(message = "Password cannot null")
    @Size(min = 8, message = "Password min 8 character")
    private String password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
