package br.com.vinicius.basiccrud.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150)
    private String street;
    @Column(length = 50)
    private String city;
    @Column(length = 50)
    private String neighborhood;
    @Column(length = 15)
    private String zipCode;
}
