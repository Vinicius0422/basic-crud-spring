package br.com.vinicius.basiccrud.services;

import br.com.vinicius.basiccrud.dtos.ContactDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ContactService {

    Page<ContactDto> findAllContacts(int page, int size);
    ContactDto findContactById(Long id);
    Page<ContactDto> findContactsByParameter(String parameter, int page, int size);
    String saveContact(ContactDto contactDto);
    String updateContact(Long id, ContactDto contactDto);
    void deleteContact(Long id);
}
