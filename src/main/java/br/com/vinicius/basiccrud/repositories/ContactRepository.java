package br.com.vinicius.basiccrud.repositories;

import br.com.vinicius.basiccrud.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
