package br.com.vinicius.basiccrud.services;

import br.com.vinicius.basiccrud.dtos.ContactDto;
import br.com.vinicius.basiccrud.exceptions.DuplicateDataException;
import br.com.vinicius.basiccrud.exceptions.ResourceNotFoundException;
import br.com.vinicius.basiccrud.mapper.ContactMapper;
import br.com.vinicius.basiccrud.models.Contact;
import br.com.vinicius.basiccrud.repositories.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImplementation implements ContactService{

    private ContactRepository contactRepository;
    private ContactMapper contactMapper;

    public ContactServiceImplementation(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public Page<ContactDto> findAllContacts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return contactRepository.findAll(pageable)
                .map(contact -> contactMapper.entityToDto(contact));
    }

    @Override
    public ContactDto findContactById(Long id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        return contactMapper.entityToDto(contact);
    }

    @Override
    public Page<ContactDto> findContactsByParameter(String parameter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return contactRepository.findContactByParameter(parameter, pageable)
                .map(contact -> contactMapper.entityToDto(contact));
    }

    @Override
    public String saveContact(ContactDto contactDto) {
        if(contactRepository.existsByEmail(contactDto.email())){
            throw new DuplicateDataException("Email in use for another contact: " + contactDto.email());
        }
        if(contactRepository.existsByPhone(contactDto.phone())){
            throw new DuplicateDataException("Phone in use for another contact: " + contactDto.phone());
        }
        Contact contact = contactMapper.dtoToEntity(contactDto);
        contactRepository.save(contact);
        return "Saved Successfully";
    }

    @Override
    public String updateContact(Long id, ContactDto contactDto) {
        Contact contactToUpdate = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No contacts found for this ID!"));

        if(contactRepository.existsByEmail(contactDto.email())){
            Long contactId = contactRepository.findContactByEmail(contactDto.email()).getId();
            if(contactId != contactToUpdate.getId()){
                throw new DuplicateDataException("Email in use for another contact: " + contactDto.email());
            }
        }
        if(contactRepository.existsByPhone(contactDto.phone())){
            Long contactId = contactRepository.findContactByPhone(contactDto.phone()).getId();
            if(contactId != contactToUpdate.getId()){
                throw new DuplicateDataException("Phone in use for another contact: " + contactDto.phone());
            }
        }

        Contact contactUpdated = contactMapper.dtoToEntity(contactDto);
        contactUpdated.setId(contactToUpdate.getId());
        contactRepository.save(contactUpdated);
        return "Updated successfully";
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
