package br.com.vinicius.basiccrud.dtos;

import jakarta.validation.constraints.*;

public record ContactDto(
        @NotBlank(message = "First name is required")
        @Size(max = 50, message = "First name must not have a maximum of 50 characters")
        String firstName,
        @NotBlank(message = "Last name is required")
        @Size(max = 50, message = "Last name must not have a maximum of 50 characters")
        String lastName,
        @NotNull(message = "Age is required")
        @Positive(message = "Age must be positive")
        @Max(value = 160, message = "Enter a valid age")
        Integer age,
        @NotBlank(message = "Email is required")
        @Size(max = 150, message = "Email must not have a maximum of 150 characters")
        @Email(message = "Enter a valid email address")
        String email,
        @NotBlank(message = "Phone is required")
        @Size(max = 50, message = "Phone must not have a maximum of 50 characters")
        String phone,
        String birthDate,
        String occupation,
        String company,
        @NotBlank(message = "Street is required")
        @Size(max = 50, message = "Street must not have a maximum of 50 characters")
        String street,
        @NotBlank(message = "Country is required")
        @Size(max = 50, message = "Country must not have a maximum of 50 characters")
        String country,
        @NotBlank(message = "City is required")
        @Size(max = 50, message = "City must not have a maximum of 50 characters")
        String city,
        @NotBlank(message = "Neighborhood is required")
        @Size(max = 50, message = "Neighborhood must not have a maximum of 50 characters")
        String neighborhood,
        @NotBlank(message = "Zip Code is required")
        @Size(max = 15, message = "Zip Code must not have a maximum of 15 characters")
        String zipCode
) { }
