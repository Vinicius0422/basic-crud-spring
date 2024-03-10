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
    @Column(nullable = false, length = 150)
    private String email;
    @Column(nullable = false, length = 50)
    private String phone;
    private LocalDate birthDate;
    private String occupation;
    private String company;
    @ManyToOne
    private Address address;
}
