package br.com.vinicius.basiccrud.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 3)
    private Integer age;
    @Column(nullable = false, length = 150, unique = true)
    private String email;
    @Column(nullable = false, length = 50, unique = true)
    private String phone;
    private LocalDate birthDate;
    private String occupation;
    private String company;
    @Column(length = 150)
    private String street;
    @Column(length = 50)
    private String country;
    @Column(length = 50)
    private String city;
    @Column(length = 50)
    private String neighborhood;
    @Column(length = 15)
    private String zipCode;

    public Contact(String firstName, String lastName, Integer age, String email, String phone,
                   LocalDate birthDate, String occupation, String company, String street, String country, String city,
                   String neighborhood, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.occupation = occupation;
        this.company = company;
        this.street = street;
        this.country = country;
        this.city = city;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
    }
}
