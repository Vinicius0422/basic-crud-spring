package br.com.vinicius.basiccrud.repositories;

import br.com.vinicius.basiccrud.models.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

     @Query("SELECT c FROM Contact c WHERE c.firstName LIKE %:parameter% OR c.lastName LIKE %:parameter% OR c.occupation %:parameter% OR c.company LIKE %:parameter%")
     Page<Contact> findContactByParameter(String parameter, Pageable pageable);

     Contact findContactByEmail(String email);
     Contact findContactByPhone(String phone);

     boolean existsByEmail(String email);
     boolean existsByPhone(String phone);
}
