package br.com.vinicius.basiccrud.repositories;

import br.com.vinicius.basiccrud.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
