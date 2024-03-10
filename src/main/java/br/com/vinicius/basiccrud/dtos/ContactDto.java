package br.com.vinicius.basiccrud.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ContactDto(
        @NotBlank
        @Max(50)
        String firstName,
        @NotBlank
        @Max(50)
        String lastName,
        @NotBlank
        @Max(3)
        Integer age,
        @NotBlank
        @Max(150)
        @Email
        String email,
        @NotBlank
        @Max(50)
        String phone,
        LocalDate birthDate,
        String occupation,
        String company,
        @NotBlank
        @Max(150)
        String street,
        @NotBlank
        @Max(50)
        String city,
        @NotBlank
        @Max(50)
        String neighborhood,
        @NotBlank
        @Max(15)
        String zipCode
) { }
